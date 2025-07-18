package com.opinai.product.application;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
@Tag("unitTest")
public abstract class UseCaseTest implements BeforeEachCallback {
    protected abstract List<Object> getMocks();

    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        Mockito.reset(getMocks().toArray());
    }
}
