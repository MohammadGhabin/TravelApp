// Generated by view binder compiler. Do not edit!
package edu.birzeit.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import edu.birzeit.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentProfileBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button LogoutButton;

  @NonNull
  public final EditText newConfirmPasswordText;

  @NonNull
  public final EditText newFirstNameText;

  @NonNull
  public final EditText newLastNameText;

  @NonNull
  public final EditText newPasswordText;

  @NonNull
  public final EditText newPhoneText;

  @NonNull
  public final Button saveChangesBTN;

  @NonNull
  public final Spinner spinnerGender;

  @NonNull
  public final TextView textProfile;

  @NonNull
  public final TextView textView;

  @NonNull
  public final TextView textView10;

  @NonNull
  public final TextView textView11;

  @NonNull
  public final TextView textView7;

  @NonNull
  public final TextView textView8;

  @NonNull
  public final TextView textView9;

  @NonNull
  public final Button viewAllBTN;

  private FragmentProfileBinding(@NonNull ConstraintLayout rootView, @NonNull Button LogoutButton,
      @NonNull EditText newConfirmPasswordText, @NonNull EditText newFirstNameText,
      @NonNull EditText newLastNameText, @NonNull EditText newPasswordText,
      @NonNull EditText newPhoneText, @NonNull Button saveChangesBTN,
      @NonNull Spinner spinnerGender, @NonNull TextView textProfile, @NonNull TextView textView,
      @NonNull TextView textView10, @NonNull TextView textView11, @NonNull TextView textView7,
      @NonNull TextView textView8, @NonNull TextView textView9, @NonNull Button viewAllBTN) {
    this.rootView = rootView;
    this.LogoutButton = LogoutButton;
    this.newConfirmPasswordText = newConfirmPasswordText;
    this.newFirstNameText = newFirstNameText;
    this.newLastNameText = newLastNameText;
    this.newPasswordText = newPasswordText;
    this.newPhoneText = newPhoneText;
    this.saveChangesBTN = saveChangesBTN;
    this.spinnerGender = spinnerGender;
    this.textProfile = textProfile;
    this.textView = textView;
    this.textView10 = textView10;
    this.textView11 = textView11;
    this.textView7 = textView7;
    this.textView8 = textView8;
    this.textView9 = textView9;
    this.viewAllBTN = viewAllBTN;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentProfileBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentProfileBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_profile, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentProfileBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.LogoutButton;
      Button LogoutButton = ViewBindings.findChildViewById(rootView, id);
      if (LogoutButton == null) {
        break missingId;
      }

      id = R.id.newConfirmPasswordText;
      EditText newConfirmPasswordText = ViewBindings.findChildViewById(rootView, id);
      if (newConfirmPasswordText == null) {
        break missingId;
      }

      id = R.id.newFirstNameText;
      EditText newFirstNameText = ViewBindings.findChildViewById(rootView, id);
      if (newFirstNameText == null) {
        break missingId;
      }

      id = R.id.newLastNameText;
      EditText newLastNameText = ViewBindings.findChildViewById(rootView, id);
      if (newLastNameText == null) {
        break missingId;
      }

      id = R.id.newPasswordText;
      EditText newPasswordText = ViewBindings.findChildViewById(rootView, id);
      if (newPasswordText == null) {
        break missingId;
      }

      id = R.id.newPhoneText;
      EditText newPhoneText = ViewBindings.findChildViewById(rootView, id);
      if (newPhoneText == null) {
        break missingId;
      }

      id = R.id.saveChangesBTN;
      Button saveChangesBTN = ViewBindings.findChildViewById(rootView, id);
      if (saveChangesBTN == null) {
        break missingId;
      }

      id = R.id.spinnerGender;
      Spinner spinnerGender = ViewBindings.findChildViewById(rootView, id);
      if (spinnerGender == null) {
        break missingId;
      }

      id = R.id.text_profile;
      TextView textProfile = ViewBindings.findChildViewById(rootView, id);
      if (textProfile == null) {
        break missingId;
      }

      id = R.id.textView;
      TextView textView = ViewBindings.findChildViewById(rootView, id);
      if (textView == null) {
        break missingId;
      }

      id = R.id.textView10;
      TextView textView10 = ViewBindings.findChildViewById(rootView, id);
      if (textView10 == null) {
        break missingId;
      }

      id = R.id.textView11;
      TextView textView11 = ViewBindings.findChildViewById(rootView, id);
      if (textView11 == null) {
        break missingId;
      }

      id = R.id.textView7;
      TextView textView7 = ViewBindings.findChildViewById(rootView, id);
      if (textView7 == null) {
        break missingId;
      }

      id = R.id.textView8;
      TextView textView8 = ViewBindings.findChildViewById(rootView, id);
      if (textView8 == null) {
        break missingId;
      }

      id = R.id.textView9;
      TextView textView9 = ViewBindings.findChildViewById(rootView, id);
      if (textView9 == null) {
        break missingId;
      }

      id = R.id.viewAllBTN;
      Button viewAllBTN = ViewBindings.findChildViewById(rootView, id);
      if (viewAllBTN == null) {
        break missingId;
      }

      return new FragmentProfileBinding((ConstraintLayout) rootView, LogoutButton,
          newConfirmPasswordText, newFirstNameText, newLastNameText, newPasswordText, newPhoneText,
          saveChangesBTN, spinnerGender, textProfile, textView, textView10, textView11, textView7,
          textView8, textView9, viewAllBTN);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
