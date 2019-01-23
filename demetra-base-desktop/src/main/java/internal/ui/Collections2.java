/*
 * Copyright 2019 National Bank of Belgium
 *
 * Licensed under the EUPL, Version 1.1 or – as soon they will be approved 
 * by the European Commission - subsequent versions of the EUPL (the "Licence");
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 *
 * http://ec.europa.eu/idabc/eupl
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the Licence is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Licence for the specific language governing permissions and 
 * limitations under the Licence.
 */
package internal.ui;

import demetra.design.MightBePromoted;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 *
 * @author Philippe Charles
 */
@MightBePromoted
@lombok.experimental.UtilityClass
public class Collections2 {

    public static <T> T[] toArray(Iterable<? extends T> iterable, Class<T> type) {
        Stream<? extends T> stream = StreamSupport.stream(iterable.spliterator(), false);
        return stream.toArray(o -> newArray(type, o));
    }

    @SuppressWarnings("unchecked")
    private static <T> T[] newArray(Class<T> type, int length) {
        return (T[]) Array.newInstance(type, length);
    }

    public static <T> void addAll(List<T> x, Iterable<? extends T> y) {
        if (y instanceof Collection) {
            x.addAll((Collection<? extends T>) y);
        } else {
            for (T o : y) {
                x.add(o);
            }
        }
    }
}
