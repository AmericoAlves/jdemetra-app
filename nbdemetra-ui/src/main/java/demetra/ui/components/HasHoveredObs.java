/*
 * Copyright 2018 National Bank of Belgium
 * 
 * Licensed under the EUPL, Version 1.1 or - as soon they will be approved 
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
package demetra.ui.components;

import demetra.ui.beans.PropertyChangeSource;
import ec.util.chart.ObsIndex;
import internal.ui.components.HasHoveredObsImpl;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 *
 * @author Philippe Charles
 */
public interface HasHoveredObs {

    static final String HOVERED_OBS_PROPERTY = "hoveredObs";

    @Nonnull
    ObsIndex getHoveredObs();

    void setHoveredObs(@Nullable ObsIndex hoveredObs);

    @Nonnull
    static HasHoveredObs of(@Nonnull PropertyChangeSource.Broadcaster broadcaster) {
        return new HasHoveredObsImpl(broadcaster);
    }
}
