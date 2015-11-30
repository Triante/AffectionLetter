package jorgeandcompany.loveletter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Firemon123 on 11/25/2015.
 */
public class ThemedDialog extends Dialog {

    public ThemedDialog(Context context, int theme) {
        super(context, theme);
    }

    public ThemedDialog(Context context) {
        super(context);
    }

    public static class Builder {
        private Context context;
        private View mainLayout;
        private String title = "";
        private String message = "";
        private String positiveButtonText;
        private String negativeButtonText;
        private View contentView;
        private Button positiveButton, negativeButton;
        private TextView messageTextView, titleTextView;
        private DialogInterface.OnClickListener
                positiveButtonClickListener,
                negativeButtonClickListener;
        private ThemedDialog dialog;
        private LinearLayout imageListView;

        public Builder(Context context) {
            this.context = context;
            dialog = new ThemedDialog(context,R.style.Dialog);
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mainLayout = inflater.inflate(R.layout.theme_dialog_layout, null);
            dialog.addContentView(mainLayout, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
            imageListView = (LinearLayout) mainLayout.findViewById(R.id.theme_icon_list);
        }
        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }
        public Builder setMessage(int message) {
            this.message = (String) context.getText(message);
            return this;
        }
        public Builder setTitle(int title) {
            this.title = (String) context.getText(title);
            return this;
        }
        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }
        public Builder setContentView(View v) {
            this.contentView = v;
            return this;
        }
        public Builder setPositiveButton(int positiveButtonText, DialogInterface.OnClickListener listener) {
            if (listener == null) listener = setNullListener();
            this.positiveButtonText = (String) context
                    .getText(positiveButtonText);
            this.positiveButtonClickListener = listener;
            return this;
        }
        public Builder setPositiveButton(String positiveButtonText, DialogInterface.OnClickListener listener) {
            if (listener == null) listener = setNullListener();
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            return this;
        }
        public Builder setNegativeButton(int negativeButtonText, DialogInterface.OnClickListener listener) {
            if (listener == null) listener = setNullListener();
            this.negativeButtonText = (String) context
                    .getText(negativeButtonText);
            this.negativeButtonClickListener = listener;
            return this;
        }
        public Builder setNegativeButton(String negativeButtonText, DialogInterface.OnClickListener listener) {
            if (listener == null) listener = setNullListener();
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            return this;
        }

        /**
         * Create the custom dialog
         */
        public ThemedDialog create() {
            positiveButton = (Button) mainLayout.findViewById(R.id.theme_positiveButton);
            negativeButton = (Button) mainLayout.findViewById(R.id.theme_negativeButton);
            messageTextView = (TextView) mainLayout.findViewById(R.id.theme_message);
            titleTextView = (TextView) mainLayout.findViewById(R.id.theme_title);
            mainLayout.findViewById(R.id.theme_layout).setBackgroundResource(SkinRes.getButtonTheme());
            titleTextView.setText(title);
            if (positiveButtonText != null) {
                positiveButton.setText(positiveButtonText);
                if (positiveButtonClickListener != null) {
                    positiveButton.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            positiveButtonClickListener.onClick(
                                    dialog,
                                    DialogInterface.BUTTON_POSITIVE);
                            dismiss();
                        }
                    });
                }
            } else {
                positiveButton.setVisibility(View.GONE);
            }
            if (negativeButtonText != null) {
                negativeButton.setText(negativeButtonText);
                if (negativeButtonClickListener != null) {
                    negativeButton.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            negativeButtonClickListener.onClick(
                                    dialog, DialogInterface.BUTTON_NEGATIVE);
                            dismiss();
                        }
                    });

                }
            } else {
                negativeButton.setVisibility(View.GONE);
            }
            // set the content message
            if (message != null) {
                messageTextView.setText(message);
            } else if (contentView != null) {
                // if no message set
                // add the contentView to the dialog body
                ((LinearLayout) mainLayout.findViewById(R.id.theme_content))
                        .removeAllViews();
                ((LinearLayout) mainLayout.findViewById(R.id.theme_content))
                        .addView(contentView,
                                new LayoutParams(
                                        LayoutParams.WRAP_CONTENT,
                                        LayoutParams.WRAP_CONTENT));
            }
            dialog.setContentView(mainLayout);
            return dialog;
        }


        public void setCancelable(boolean flag) {
            dialog.setCancelable(flag);
        }

        public void show() {
            create();
            dialog.show();
        }

        public void dismiss() {
            dialog.dismiss();
        }

        private OnClickListener setNullListener() {
            OnClickListener listener = new OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dismiss();
                }
            };
            return listener;
        }

        public void setView(View view) {
            imageListView.addView(view);
        }

    }

}
