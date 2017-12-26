package com.inventyfy.architecture.ui.home;

import com.inventyfy.architecture.base.activity.AbstractBaseNormalActivity;

public abstract class AbstractBaseHomeActivity extends AbstractBaseNormalActivity {

    public interface UiHomeInteraction {
        void createToolbar(final boolean isBackNavigation, final String title);
    }
}
