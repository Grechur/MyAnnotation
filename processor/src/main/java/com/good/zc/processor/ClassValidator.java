package com.good.zc.processor;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import static java.lang.reflect.Modifier.PRIVATE;

/**
 * Created by Zc on 2018/4/23.
 */

final class ClassValidator
{
    static boolean isPrivate(Element annotatedClass)
    {
        return annotatedClass.getModifiers().contains(PRIVATE);
    }

    static String getClassName(TypeElement type, String packageName)
    {
        int packageLen = packageName.length() + 1;
        return type.getQualifiedName().toString().substring(packageLen)
                .replace('.', '$');
    }
}
