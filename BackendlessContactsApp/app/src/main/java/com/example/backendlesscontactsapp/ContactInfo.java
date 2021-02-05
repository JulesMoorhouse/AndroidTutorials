package com.example.backendlesscontactsapp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class ContactInfo extends AppCompatActivity {

    private View mProgressView;
    private View mLoginFormView;
    private TextView tvLoad;

    TextView tvInfoChar, tvInfoName;
    ImageView ivCall, ivMail, ivEdit, ivDelete;
    EditText etInfoName, etInfoMail, etInfoPhone;
    Button btnInfoSubmit;

    boolean edit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        tvLoad = findViewById(R.id.tvLoad);

        tvInfoChar = findViewById(R.id.tvInfoChar);
        tvInfoName = findViewById(R.id.tvInfoName);

        ivCall = findViewById(R.id.ivCall);
        ivMail = findViewById(R.id.ivMail);
        ivEdit = findViewById(R.id.ivEdit);
        ivDelete = findViewById(R.id.ivDelete);

        etInfoName = findViewById(R.id.etInfoName);
        etInfoMail = findViewById(R.id.etInfoMail);
        etInfoPhone = findViewById(R.id.etInfoPhone);

        btnInfoSubmit = findViewById(R.id.btnInfoSubmit);

        etInfoName.setVisibility(View.GONE);
        etInfoMail.setVisibility(View.GONE);
        etInfoPhone.setVisibility(View.GONE);
        btnInfoSubmit.setVisibility(View.GONE);

        final int index = getIntent().getIntExtra("index", 0);

        Contact contact = ApplicationClass.contacts.get(index);

        etInfoName.setText(contact.getName());
        etInfoMail.setText(contact.getEmail());
        etInfoPhone.setText(contact.getNumber());

        tvInfoChar.setText(contact.getName().toUpperCase().charAt(0) + "");
        tvInfoName.setText(contact.getName());

        ivCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String uri = "tel:" + ApplicationClass.contacts.get(index).getNumber();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(uri));
                startActivity(intent);
            }
        });

        ivMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/html");
                intent.putExtra(Intent.EXTRA_EMAIL, ApplicationClass.contacts.get(index).getEmail());
                startActivity(intent.createChooser(intent, "Send mail to " + ApplicationClass.contacts.get(index).getName()));
            }
        });

        ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                edit = !edit;

                if (edit)
                {
                    etInfoName.setVisibility(View.VISIBLE);
                    etInfoMail.setVisibility(View.VISIBLE);
                    etInfoPhone.setVisibility(View.VISIBLE);
                    btnInfoSubmit.setVisibility(View.VISIBLE);
                }
                else
                {
                    etInfoName.setVisibility(View.GONE);
                    etInfoMail.setVisibility(View.GONE);
                    etInfoPhone.setVisibility(View.GONE);
                    btnInfoSubmit.setVisibility(View.GONE);
                }
            }
        });

        ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder dialog = new AlertDialog.Builder(ContactInfo.this);
                dialog.setMessage("Are you sure you want to delete this contact?");
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        showProgress(true);
                        tvLoad.setText("Deleting contact please wait");

                        Backendless.Persistence.of(Contact.class).remove(ApplicationClass.contacts.get(index), new AsyncCallback<Long>() {
                            @Override
                            public void handleResponse(Long response) {

                                ApplicationClass.contacts.remove((index));
                                Toast.makeText(ContactInfo.this, "Contact successfully removed!", Toast.LENGTH_SHORT).show();
                                setResult(RESULT_OK);
                                ContactInfo.this.finish();
                            }

                            @Override
                            public void handleFault(BackendlessFault fault) {

                                Toast.makeText(ContactInfo.this, "Error: " + fault.getMessage(), Toast.LENGTH_SHORT).show();
                                showProgress(false);
                            }
                        });

                    }
                });

                dialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                dialog.show();
            }
        });

        btnInfoSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = etInfoName.getText().toString().trim();
                String mail = etInfoMail.getText().toString().trim();
                String phone = etInfoPhone.getText().toString().trim();

                if (name.isEmpty() || mail.isEmpty() || phone.isEmpty())
                {
                    Toast.makeText(ContactInfo.this, "Please enter all details!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    ApplicationClass.contacts.get(index).setName(name);
                    ApplicationClass.contacts.get(index).setNumber(phone);
                    ApplicationClass.contacts.get(index).setEmail(mail);
                    
                    showProgress(true);
                    tvLoad.setText("Updating contact... please wait...");

                    Backendless.Persistence.save(ApplicationClass.contacts.get(index), new AsyncCallback<Contact>() {
                        @Override
                        public void handleResponse(Contact response) {

                            Contact contact = ApplicationClass.contacts.get(index);

                            tvInfoChar.setText(contact.getName().toUpperCase().charAt(0) + "");
                            tvInfoName.setText(contact.getName());
                            Toast.makeText(ContactInfo.this, "Contact successfully updated!", Toast.LENGTH_SHORT).show();
                            showProgress(false);
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {

                            Toast.makeText(ContactInfo.this, "Error: " + fault.getMessage(), Toast.LENGTH_SHORT).show();
                            showProgress(false);
                        }
                    });

                }
            }
        });
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });

            tvLoad.setVisibility(show ? View.VISIBLE : View.GONE);
            tvLoad.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    tvLoad.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            tvLoad.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
}