/*
 * The MIT License
 *
 *   Copyright (c) 2016, Mahmoud Ben Hassine (mahmoud.benhassine@icloud.com)
 *
 *   Permission is hereby granted, free of charge, to any person obtaining a copy
 *   of this software and associated documentation files (the "Software"), to deal
 *   in the Software without restriction, including without limitation the rights
 *   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *   copies of the Software, and to permit persons to whom the Software is
 *   furnished to do so, subject to the following conditions:
 *
 *   The above copyright notice and this permission notice shall be included in
 *   all copies or substantial portions of the Software.
 *
 *   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *   THE SOFTWARE.
 */

package io.github.benas.randombeans;

import io.github.benas.randombeans.api.BeanPopulationException;
import io.github.benas.randombeans.api.Randomizer;
import io.github.benas.randombeans.randomizers.SkipRandomizer;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

import static io.github.benas.randombeans.util.CollectionUtils.randomElementOf;
import static io.github.benas.randombeans.util.ReflectionUtils.*;

/**
 * Component that encapsulate the logic of generating a random value for a given field.
 * It collaborates with a {@link BeanPopulator} used whenever the field is a user defined type.
 *
 * @author Mahmoud Ben Hassine (mahmoud.benhassine@icloud.com)
 */
public class FieldPopulator {

    private BeanPopulator beanPopulator;

    private ArrayPopulator arrayPopulator;

    private CollectionPopulator collectionPopulator;

    private MapPopulator mapPopulator;

    private RandomizerProvider randomizerProvider;

    private boolean scanClasspathForConcreteClasses;

    FieldPopulator(final BeanPopulator beanPopulator, final RandomizerProvider randomizerProvider, final ObjectFactory objectFactory) {
        this.beanPopulator = beanPopulator;
        this.randomizerProvider = randomizerProvider;
        this.arrayPopulator = new ArrayPopulator(beanPopulator);
        this.collectionPopulator = new CollectionPopulator(beanPopulator, objectFactory);
        this.mapPopulator = new MapPopulator(beanPopulator, objectFactory);
    }

    void populateField(final Object target, final Field field, final PopulatorContext context) throws IllegalAccessException {
        Randomizer<?> randomizer = randomizerProvider.getRandomizerByField(field);
        if (randomizer instanceof SkipRandomizer) {
            return;
        }
        context.pushStackItem(new PopulatorContextStackItem(target, field));
        Object value;
        if (randomizer != null) {
            value = randomizer.getRandomValue();
        } else {
            value = generateRandomValue(field, context);
        }
        setProperty(target, field, value);
        context.popStackItem();
    }

    private Object generateRandomValue(final Field field, final PopulatorContext context) throws IllegalAccessException {
        Class<?> fieldType = field.getType();
        Type fieldGenericType = field.getGenericType();

        Object value;
        if (isArrayType(fieldType)) {
            value = arrayPopulator.getRandomArray(fieldType);
        } else if (isCollectionType(fieldType)) {
            value = collectionPopulator.getRandomCollection(field);
        } else if (isMapType(fieldType)) {
            value = mapPopulator.getRandomMap(field);
        } else {
            if (scanClasspathForConcreteClasses && isAbstract(fieldType)) {
                Class<?> randomConcreteSubType = randomElementOf(filterSameParameterizedTypes(getPublicConcreteSubTypesOf(fieldType), fieldGenericType));
                if (randomConcreteSubType == null) {
                    throw new BeanPopulationException("Unable to find a matching concrete subtype of type: " + fieldType);
                } else {
                    value = beanPopulator.populate(randomConcreteSubType);
                }
            } else {
                value = beanPopulator.doPopulateBean(fieldType, context);
            }
        }
        return value;
    }

    void setScanClasspathForConcreteClasses(boolean scanClasspathForConcreteClasses) {
        this.scanClasspathForConcreteClasses = scanClasspathForConcreteClasses;
    }
}
