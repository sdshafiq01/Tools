package com.thbd.tools;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.util.Patterns;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.browser.customtabs.CustomTabColorSchemeParams;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Tools {


    private static final String TAG = "Custom Basic Tools";

    public static boolean isNetConnect() {
        try {
            String commend = "ping -c 1 google.com";
            return (Runtime.getRuntime().exec(commend).waitFor() == 0);
        } catch (Exception e) {
            return false;
        }


    }


    public static void setSystemBarColor(String color , Activity act) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = act.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ((Window) window).setStatusBarColor(Color.parseColor(color));

        }
    }

    public static void setSystemBarColor(@ColorRes int color, Activity act) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = act.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ((Window) window).setStatusBarColor(act.getResources().getColor(color));
        }
    }

    public static void setSystemBarLight(Activity act) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View view = act.findViewById(android.R.id.content);
            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
        }
    }

    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return model;
        } else {
            return manufacturer + " " + model;
        }
    }

    public static String getAndroidVersion() {
        return Build.VERSION.RELEASE + "";
    }
    public static int getVersionCode( Context ctx) {
        try {
            PackageManager manager = ctx.getPackageManager();
            PackageInfo info = manager.getPackageInfo(ctx.getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            return -1;
        }
    }

    public static String getVersionName( Context ctx) {
        try {
            PackageManager manager = ctx.getPackageManager();
            PackageInfo info = manager.getPackageInfo(ctx.getPackageName(), 0);
            return "App version: " + info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return "Unknown Version";
        }
    }

    public static String getVersionNamePlain( Context ctx) {
        try {
            PackageManager manager = ctx.getPackageManager();
            PackageInfo info = manager.getPackageInfo(ctx.getPackageName(), 0);
            return info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return "Unknown Version";
        }
    }

    @SuppressLint("HardwareIds")
    public static String getDeviceID( Context ctx ) {
        String deviceID = Build.SERIAL;
        if (deviceID == null || deviceID.trim().isEmpty() || deviceID.equalsIgnoreCase("unknown") || deviceID.equals("0")) {
            try {
                deviceID = Settings.Secure.getString(ctx.getContentResolver(), Settings.Secure.ANDROID_ID);
            } catch (Exception e) {
            }
        }
        return deviceID;
    }

    public static String getFormattedDateSimple(Long dateTime) {
        SimpleDateFormat newFormat = new SimpleDateFormat("dd MMM yy hh:mm");
        return newFormat.format(new Date(dateTime));
    }


    public  void displayImage(Context ctx, ImageView imgs, String url) {
        try {
            Glide.with(ctx.getApplicationContext()).load(url)
                    .transition(withCrossFade())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgs);
        } catch (Exception e) {
        }
    }

    public  void displayImageCircle(Context ctx,ImageView img, Bitmap bmp) {
        try {
            Glide.with(ctx.getApplicationContext()).load(bmp)
                    .transition(withCrossFade())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .apply(RequestOptions.circleCropTransform())
                    .into(img);
        } catch (Exception e) {
        }
    }

    public  void displayImageCircle(Context ctx,ImageView img, String url) {
        try {
            Glide.with(ctx.getApplicationContext()).load(url)
                    .transition(withCrossFade())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .apply(RequestOptions.circleCropTransform())
                    .into(img);
        } catch (Exception e) {
        }
    }

    public  void displayImageCircle(Context ctx,ImageView img, String url, float thumb) {
        try {
            Glide.with(ctx.getApplicationContext()).load(url)
                    .transition(withCrossFade())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .apply(RequestOptions.circleCropTransform())
                    .thumbnail(thumb)
                    .into(img);
        } catch (Exception e) {
        }
    }

    public  void displayImageThumb( Context ctx,ImageView img, String url, float thumb) {
        try {
            Glide.with(ctx.getApplicationContext()).load(url)
                    .transition(withCrossFade())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .thumbnail(thumb)
                    .into(img);
        } catch (Exception e) {
        }
    }

    public static void clearImageCacheOnBackground(final Context ctx) {
        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Glide.get(ctx).clearDiskCache();
                }
            }).start();
        } catch (Exception e) {
        }
    }

    public static int dpToPx(Context ctx, int dp) {
        Resources r = ctx.getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    public static void directLinkToBrowser(Activity act, String url) {
        if (!URLUtil.isValidUrl(url)) {
            Toast.makeText(act, "Ops, Cannot open url", Toast.LENGTH_LONG).show();
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        act.startActivity(intent);
    }

    public static boolean isEmailValid(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static Drawable tintDrawable(Context ctx, @DrawableRes int drawable, @ColorRes int color) {
        Drawable unwrappedDrawable = AppCompatResources.getDrawable(ctx, drawable);
        Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
        DrawableCompat.setTint(wrappedDrawable, ctx.getResources().getColor(color));
        return wrappedDrawable;
    }

    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String html) {
        if (html == null) {
            return new SpannableString("");
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(html);
        }
    }

    public static void hideKeyboard( Activity act) {
        InputMethodManager imm = (InputMethodManager) act.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = act.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(act);
        }
        assert imm != null;
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void showAlert(Activity activity, String msg, String positiveBtnText, @ColorRes int tintColor, @DrawableRes int setIcon) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(activity);
        builder.setIcon(Tools.tintDrawable(activity, setIcon, tintColor));
        builder.setTitle(msg);
        builder.setCancelable(true);
        builder.setPositiveButton(positiveBtnText, null);
        builder.show();
    }

    public static class progressDialog {
        ProgressDialog progressDialog;
        public  void show(Activity activity){
            progressDialog = new ProgressDialog(activity);

//        progressDialog = new ProgressDialog(HomeActivity.this);
            progressDialog.show();
            progressDialog.setContentView(R.layout.loading_layout);
            //Dialog Layout Width and height set
            progressDialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT);
//       Set Transparent background ==========
            progressDialog.getWindow().setBackgroundDrawableResource(
                    android.R.color.transparent);
            // Set out Side touch protector
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setCancelable(false);
        }


        public  void cancel() {
            // Log.e("TAG", "Cancel: progress" );
            progressDialog.cancel();
            progressDialog.hide();
//        progressDialog =null;
        }
    }

    public static void showAlert(Activity act,String msg, String positiveBtnText) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(act);
        builder.setTitle(msg);
        builder.setCancelable(true);
        builder.setPositiveButton(positiveBtnText, null);
        builder.show();
    }

    public static void showToast(Activity act,String msg) {
        Toast.makeText(act, msg, Toast.LENGTH_SHORT).show();
    }

    public static void snackBarError(Activity act, View view, String msg) {
        final Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_SHORT);
        snackbar.setBackgroundTint(act.getResources().getColor(android.R.color.holo_red_dark));
        try {
            snackbar.show();
        } catch (Exception e) {
        }
    }

    public static void snackBarSuccess(Activity act, View view, String msg) {
        final Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_SHORT);
        snackbar.setBackgroundTint(act.getResources().getColor(android.R.color.holo_green_light));
        try {
            snackbar.show();
        } catch (Exception e) {
        }
    }

    public static String getSalePercentage(String sale_price,String regular_price ) {
        double saleprice = Double.parseDouble(sale_price);
        double regularprice = Double.parseDouble(regular_price);
        double off = 100 * (regularprice - saleprice) / regularprice;
        int integerValue = (int) Math.round(off);
        return integerValue + "%";
    }

    public static void directLinkCustomTab(Activity act,String url) {
        if (!URLUtil.isValidUrl(url)) {
            Toast.makeText(act, "Ops, Cannot open url", Toast.LENGTH_LONG).show();
            return;
        }
        int color = ResourcesCompat.getColor(act.getResources(), android.R.color.holo_blue_dark, null);
        int secondaryColor = ResourcesCompat.getColor(act.getResources(), android.R.color.holo_blue_dark, null);

        CustomTabsIntent.Builder intentBuilder = new CustomTabsIntent.Builder();
        CustomTabColorSchemeParams defaultColors = new CustomTabColorSchemeParams.Builder()
                .setToolbarColor(color)
                .setSecondaryToolbarColor(secondaryColor)
                .build();
        intentBuilder.setDefaultColorSchemeParams(defaultColors);
        intentBuilder.setShowTitle(true);
        intentBuilder.setUrlBarHidingEnabled(true);

        CustomTabsHelper.openCustomTab(act, intentBuilder.build(), Uri.parse(url), (activity1, uri) -> {
            act.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        });
    }


}
