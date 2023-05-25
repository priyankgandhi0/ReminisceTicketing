package com.example.reminisce_ticketing.utils;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.Objects;

public class FragmentUtils {

    /**
     * This method clears back stack and loads fragment in a root.
     *
     * @return true if loaded successfully, false otherwise
     */
    public static boolean loadFragmentInRoot1(FragmentActivity fragmentActivity,
                                              int fragmentContainerId, Class<? extends Fragment> fragmentClass,
                                              Bundle bundle, String tag, FragmentManager fragmentManager) {
        boolean status = false;

//        FragmentManager fragmentManager = fragmentActivity
//                .getSupportFragmentManager();
        // remove all fragments from back stack
        fragmentManager.popBackStack(null,
                FragmentManager.POP_BACK_STACK_INCLUSIVE);
        // add new fragment
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                android.R.anim.fade_out, android.R.anim.fade_in,
                android.R.anim.fade_out);
        Fragment fragment;
        try {
            fragment = fragmentClass.newInstance();
            fragment.setArguments(bundle);
            fragmentTransaction.replace(fragmentContainerId, fragment, tag)
                    .commit();
            // finish pending transactions
            fragmentManager.executePendingTransactions();
            status = true;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return status;
    }

    public static boolean loadFragmentInRoot(FragmentActivity fragmentActivity,
                                             int fragmentContainerId, Class<? extends Fragment> fragmentClass,
                                             Bundle bundle, String tag) {
        return loadFragmentInRoot1(fragmentActivity, fragmentContainerId, fragmentClass, bundle, tag,
                fragmentActivity.getSupportFragmentManager());
    }

    public static boolean loadChildFragmentInRoot(FragmentActivity fragmentActivity,
                                                  int fragmentContainerId, Class<? extends Fragment> fragmentClass,
                                                  Bundle bundle, String tag, FragmentManager childFragmentManager) {
        return loadFragmentInRoot1(fragmentActivity, fragmentContainerId, fragmentClass, bundle, tag,
                childFragmentManager);
    }

    /**
     * This method loads fragment in a back stack.
     *
     * @return true if loaded successfully, false otherwise
     */
    public static boolean loadFragmentInBackStack1(
            FragmentActivity fragmentActivity, int fragmentContainerId,
            Class<? extends Fragment> fragmentClass, Bundle bundle, String tag, FragmentManager fragmentManager) {
        boolean status = false;
        try {
//            FragmentManager fragmentManager = fragmentActivity
//                    .getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager
                    .beginTransaction();
            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                    android.R.anim.fade_out, android.R.anim.fade_in,
                    android.R.anim.fade_out);

            Fragment fragment = fragmentClass.newInstance();
            fragment.setArguments(bundle);
            fragmentTransaction.replace(fragmentContainerId, fragment, tag)
                    .addToBackStack(tag).commit();
            // finish pending transactions
//			fragmentManager.executePendingTransactions();
            status = true;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return status;
    }

    public static boolean loadFragmentInBackStack1New(
            Activity fragmentActivity, int fragmentContainerId,
            Class<? extends Fragment> fragmentClass, Bundle bundle, String prevTag, String tag, FragmentManager fragmentManager) {
        boolean status = false;
        try {
//            FragmentManager fragmentManager = fragmentActivity
//                    .getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager
                    .beginTransaction();
            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                    android.R.anim.fade_out, android.R.anim.fade_in,
                    android.R.anim.fade_out);

            fragmentTransaction.hide(Objects.requireNonNull(fragmentManager.findFragmentByTag(prevTag)));
            Fragment fragment = fragmentClass.newInstance();
            fragment.setArguments(bundle);
            fragmentTransaction.add(fragmentContainerId, fragment, tag)
                    .addToBackStack(tag).commit();
            // finish pending transactions
//			fragmentManager.executePendingTransactions();
            status = true;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return status;
    }

    public static boolean loadFragmentInBackStack(FragmentActivity fragmentActivity,
                                                  int fragmentContainerId, Class<? extends Fragment> fragmentClass,
                                                  Bundle bundle, String tag) {
        return loadFragmentInBackStack1(fragmentActivity, fragmentContainerId, fragmentClass, bundle, tag,
                fragmentActivity.getSupportFragmentManager());
    }

    public static boolean loadFragmentInBackStack(FragmentActivity fragmentActivity,
                                                  int fragmentContainerId, Class<? extends Fragment> fragmentClass,
                                                  Bundle bundle, String tag, FragmentManager childFragmentManager) {
        return loadFragmentInBackStack1(fragmentActivity, fragmentContainerId, fragmentClass, bundle, tag,
                childFragmentManager);
    }

    /**
     * This method counts no of fragments in a back stack.
     *
     * @return total no of fragments in a back stack
     */
    public static int getNumberOfFragmentsInBackStack(
            FragmentActivity fragmentActivity) {
        // make sure transactions are finished before reading back stack count
        FragmentManager fragmentManager = fragmentActivity
                .getSupportFragmentManager();
        fragmentManager.executePendingTransactions();

        return fragmentManager.getBackStackEntryCount();
    }

    /**
     * get current visible fragment from back stack
     *
     * @return fragment tag
     */
    public static String getVisibleFragmentFromBackStack(
            FragmentActivity fragmentActivity) {
        int count = getNumberOfFragmentsInBackStack(fragmentActivity);
        if (count != 0) {
            return fragmentActivity.getSupportFragmentManager()
                    .getBackStackEntryAt(count - 1).getName();
        }
        return null;
    }

    /**
     * remove Fragment From BackStack
     */
    public static String removeFragmentFromBackStack(
            FragmentActivity fragmentActivity,
            Class<? extends Fragment> fragmentClass, String tag) {

        FragmentManager fragmentManager = fragmentActivity
                .getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        Fragment fragment = fragmentManager.findFragmentByTag(tag);

        if (fragment != null) {
            fragmentTransaction.remove(fragment).commit();
        }
        fragmentManager.popBackStack();
        return null;
    }
}
