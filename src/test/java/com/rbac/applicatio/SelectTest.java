package com.rbac.applicatio;

import com.system.util.base.DumperUtils;
import com.system.util.custom.Select;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class SelectTest {

    @Test
    public void invokSelectTest() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Map<String, String> option = new Select.SelectBuilder("YesNo", "select").build().toOption();
        DumperUtils.dump(option);
    }

}
