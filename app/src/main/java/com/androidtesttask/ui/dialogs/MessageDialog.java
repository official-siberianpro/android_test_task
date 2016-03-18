package com.androidtesttask.ui.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.mig35.injectorlib.utils.inject.InjectSavedState;

public class MessageDialog extends BaseDialog {

    public static final String TAG = MessageDialog.class.getSimpleName();

    @InjectSavedState
    private String mTitle;
    @InjectSavedState
    private String mMessage;

    @InjectSavedState
    private String mPositiveButton;

    @InjectSavedState
    private int mDialogCode;

    public interface OnResultListener {
        void onResult(int dialogCode, Result result);
    }

    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        setCancelable(true);

        builder.setTitle(mTitle);
        builder.setMessage(mMessage);
        builder.setPositiveButton(mPositiveButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialogInterface, final int i) {
                onResult(Result.POSITIVE);
            }
        });
        return builder.create();
    }

    @Override
    public void onCancel(final DialogInterface dialog) {
        super.onCancel(dialog);
        onResult(Result.CANCEL);
    }

    private void onResult(final Result result) {
        if (getActivity() instanceof OnResultListener) {
            dismiss();
            ((OnResultListener) getActivity()).onResult(mDialogCode, result);
        }
    }

    public static Builder build(final Activity activity) {
        return new Builder(activity);
    }

    public static class Builder {

        private final Activity mActivity;
        private final MessageDialog mDialog;

        Builder(final Activity activity) {
            mActivity = activity;
            mDialog = new MessageDialog();
        }

        public void show() {
            mDialog.show(mActivity.getFragmentManager(), TAG);
        }

        public Builder title(final int titleId) {
            mDialog.mTitle = mActivity.getString(titleId);
            return this;
        }

        public Builder title(final String title) {
            mDialog.mTitle = title;
            return this;
        }

        public Builder message(final int messageId) {
            message(mActivity.getString(messageId));
            return this;
        }

        public Builder message(final String message) {
            mDialog.mMessage = message;
            return this;
        }

        public Builder positive(final int buttonId) {
            positive(mActivity.getString(buttonId));
            return this;
        }

        public Builder positive(final String button) {
            mDialog.mPositiveButton = button;
            return this;
        }

        public Builder dialogCode(final int dialogCode) {
            mDialog.mDialogCode = dialogCode;
            return this;
        }

    }

    public enum Result {
        POSITIVE, CANCEL
    }

}
