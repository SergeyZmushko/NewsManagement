package com.mjc.school.service.validator;

import com.mjc.school.service.validator.checker.ConstraintChecker;
import com.mjc.school.service.validator.constraint.Constraint;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@SuppressWarnings({"unchecked", "rawtypes"})
public class ValidatorImpl implements Validator {

    private final Map<Class<? extends Annotation>, ConstraintChecker> checkerMap;

    public ValidatorImpl(final List<ConstraintChecker> checkers){
        this.checkerMap = checkers
                .stream()
                .collect(Collectors.toMap(ConstraintChecker::getType, Function.identity()));
    }

    @Override
    public Set<ConstraintViolation> validate(Object object) {
        if (object == null){
            return Collections.emptySet();
        }
        var violations = new HashSet<ConstraintViolation>();
        for (var declaredField : object.getClass().getDeclaredFields()){
            validateField(violations, declaredField, object);
        }
        return violations;
    }

    private void validateObject(Set<ConstraintViolation> violations, final Object object) {
        if (object == null) {
            return;
        }
        for (var declaredField : object.getClass().getDeclaredFields()) {
            validateField(violations, declaredField, object);
        }
    }

    private void validateField(Set<ConstraintViolation> violations, Field field, Object instance){
        for (var declaredAnnotation : field.getDeclaredAnnotations()){
            var annotationType = declaredAnnotation.annotationType();
            if (annotationType.isAnnotationPresent(Constraint.class)){
                try {
                    if (field.trySetAccessible() && field.canAccess(instance)){
                        var value = field.get(instance);
                        var checker = checkerMap.get(annotationType);
                        if (checker != null && !checker.check(value, annotationType.cast(declaredAnnotation))){
                            violations.add(
                                    new ConstraintViolation("Constraint '%s' violated for the value '%s'".formatted(
                                            annotationType.getSimpleName(),
                                            value)
                                    )
                            );
                        }
                        validateObject(violations, value);
                    }
                }catch (IllegalAccessException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
