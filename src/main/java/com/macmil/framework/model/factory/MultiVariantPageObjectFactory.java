package com.macmil.framework.model.factory;

import com.macmil.framework.model.BasePageObject;
import com.macmil.framework.model.PageVariantReader;
import com.macmil.framework.webdriver.DriverManager;
import org.openqa.selenium.support.PageFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Factory producing correct variant of page object class
 */
public class MultiVariantPageObjectFactory {

    /**
     * Produces correct variant of page object class
     * @param clazz root page object class that must extend BasePageObject class
     * @return instance of page object class that correctly applies page variant condition
     * @param <T> root page object class
     */
    @SuppressWarnings(value = "unchecked")
    public <T extends BasePageObject<T>> T create(Class<T> clazz) {
        T retval = null;

        for (String variant: loadPageVariants(clazz)) {
            T instance;

            try {
                instance = (T) Class.forName(variant).getDeclaredConstructor().newInstance();
                initPageElements(instance);
                if (instance.applies()) {
                    retval = instance;
                    break;
                }
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException(String.format("%s class could not be found!", clazz), e);
            } catch (NoSuchMethodException | IllegalAccessException e) {
                throw new IllegalStateException(String.format("%s class has no default constructor!", clazz), e);
            } catch (InstantiationException e) {
                throw new IllegalStateException(String.format("%s class could not be instantiated!", clazz), e);
            } catch (InvocationTargetException e) {
                throw new IllegalStateException(String.format("%s class constructor threw an exception!", clazz), e);
            }
        }

        return retval;
    }

    private <T extends BasePageObject<T>> List<String> loadPageVariants(Class<T> clazz) {
        return PageVariantReader.getPageVariants(clazz.getName());
    }

    private <T extends BasePageObject<T>> void initPageElements(T instance) {
        PageFactory.initElements(DriverManager.getInstance().getDriver(), instance);
    }
}
