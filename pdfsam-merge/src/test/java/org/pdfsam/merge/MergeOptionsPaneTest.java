/* 
 * This file is part of the PDF Split And Merge source code
 * Created on 09/set/2014
 * Copyright 2013-2014 by Andrea Vacondio (andrea.vacondio@gmail.com).
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as 
 * published by the Free Software Foundation, either version 3 of the 
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.pdfsam.merge;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.function.Consumer;

import javafx.scene.Parent;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.loadui.testfx.GuiTest;
import org.loadui.testfx.categories.TestFX;
import org.loadui.testfx.utils.FXTestUtils;
import org.pdfsam.test.ClearEventStudioRule;
import org.sejda.model.outline.OutlinePolicy;

/**
 * @author Andrea Vacondio
 *
 */
@Category(TestFX.class)
public class MergeOptionsPaneTest extends GuiTest {

    @ClassRule
    public static ClearEventStudioRule CLEAR_STUDIO = new ClearEventStudioRule();

    private MergeParametersBuilder builder;
    private Consumer<String> onError;

    @Before
    public void setUp() {
        builder = mock(MergeParametersBuilder.class);
        onError = mock(Consumer.class);
    }

    @Override
    protected Parent getRootNode() {
        return new MergeOptionsPane();
    }

    @Test
    public void validSteps() throws Exception {
        click("#blankIfOddCheck");
        MergeOptionsPane victim = find(".pdfsam-container");
        FXTestUtils.invokeAndWait(() -> victim.apply(builder, onError), 2);
        verify(builder).outlinePolicy(eq(OutlinePolicy.RETAIN));
        verify(builder).blankPageIfOdd(true);
        verify(builder).copyFormFields(false);
        verify(onError, never()).accept(anyString());
    }
}