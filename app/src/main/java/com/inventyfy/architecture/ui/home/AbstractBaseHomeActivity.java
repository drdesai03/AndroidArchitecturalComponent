package com.inventyfy.architecture.ui.home;

import android.support.v4.app.Fragment;

import com.inventyfy.architecture.base.activity.AbstractBaseNormalActivity;
import com.inventyfy.architecture.base.activity.ToolbarListener;

public abstract class AbstractBaseHomeActivity extends AbstractBaseNormalActivity {

    public interface UiHomeInteraction {
        void createToolbar(final boolean isBackNavigation, final String title);

        void createToolbarWithCallback(final boolean isBackNavigation, final String title, final ToolbarListener toolbarListener);

        void updateFragment(final Fragment fragment, final boolean addToBackStack);
    }
}
