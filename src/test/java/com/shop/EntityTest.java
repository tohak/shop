package com.shop;

import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.rule.impl.SetterMustExistRule;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class EntityTest {
    Validator validator;
    private String packageName = "com.shop.domain.entity";
    private List<PojoClass> pojoClasses;

    @Before
    public void setup() {
        pojoClasses = PojoClassFactory.getPojoClassesRecursively(packageName, null);
    }

    @Test
    public void validate() {
        validator = ValidatorBuilder.create()
                .with(new SetterMustExistRule(),
                        new GetterMustExistRule())
                .with(new SetterTester(),
                        new GetterTester())
                .build();

        validator.validate(pojoClasses);
    }
}
