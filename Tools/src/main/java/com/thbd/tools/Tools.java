package com.thbd.tools;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

import android.annotation.SuppressLint;
import android.app.Activity;
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
import android.util.Log;
import android.util.Patterns;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
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

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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

    public static void setSystemBarColor(String color, Activity act) {
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

    public static int getVersionCode(Context ctx) {
        try {
            PackageManager manager = ctx.getPackageManager();
            PackageInfo info = manager.getPackageInfo(ctx.getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            return -1;
        }
    }

    public static String getVersionName(Context ctx) {
        try {
            PackageManager manager = ctx.getPackageManager();
            PackageInfo info = manager.getPackageInfo(ctx.getPackageName(), 0);
            return "App version: " + info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return "Unknown Version";
        }
    }

    public static String getVersionNamePlain(Context ctx) {
        try {
            PackageManager manager = ctx.getPackageManager();
            PackageInfo info = manager.getPackageInfo(ctx.getPackageName(), 0);
            return info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return "Unknown Version";
        }
    }

    @SuppressLint("HardwareIds")
    public static String getDeviceID(Context ctx) {
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

    public static void displayImage(Context ctx, ImageView imgs, String url) {
        try {
            Glide.with(ctx.getApplicationContext()).load(url)
                    .transition(withCrossFade())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgs);
        } catch (Exception e) {
        }
    }

    public static void displayImageCircle(Context ctx, ImageView img, Bitmap bmp) {
        try {
            Glide.with(ctx.getApplicationContext()).load(bmp)
                    .transition(withCrossFade())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .apply(RequestOptions.circleCropTransform())
                    .into(img);
        } catch (Exception e) {
        }
    }

    public static void displayImageCircle(Context ctx, ImageView img, String url) {
        try {
            Glide.with(ctx.getApplicationContext()).load(url)
                    .transition(withCrossFade())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .apply(RequestOptions.circleCropTransform())
                    .into(img);
        } catch (Exception e) {
        }
    }

    public static void displayImageCircle(Context ctx, ImageView img, String url, float thumb) {
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

    public static void displayImageThumb(Context ctx, ImageView img, String url, float thumb) {
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

    public static void hideKeyboard(Activity act) {
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

    public static void showAlert(Activity act, String msg, String positiveBtnText) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(act);
        builder.setTitle(msg);
        builder.setCancelable(true);
        builder.setPositiveButton(positiveBtnText, null);
        builder.show();
    }

    public static void showToast(Activity act, String msg) {
        Toast.makeText(act, msg, Toast.LENGTH_SHORT).show();
    }

    public static void snackBarError(Activity act, String msg) {
        View view1 = act.findViewById(android.R.id.content);
        final Snackbar snackbar = Snackbar.make(view1, msg, Snackbar.LENGTH_LONG);
        snackbar.setBackgroundTint(act.getResources().getColor(android.R.color.holo_red_dark));
        try {
            snackbar.show();
        } catch (Exception e) {
        }
    }

    public static void snackBarSuccess(Activity act, String msg) {
        View view1 = act.findViewById(android.R.id.content);
        final Snackbar snackbar = Snackbar.make(view1, msg, Snackbar.LENGTH_LONG);
        snackbar.setBackgroundTint(act.getResources().getColor(android.R.color.holo_green_light));
        try {
            snackbar.show();
        } catch (Exception e) {
        }
    }

    public static String getSalePercentage(String sale_price, String regular_price) {
        double saleprice = Double.parseDouble(sale_price);
        double regularprice = Double.parseDouble(regular_price);
        double off = 100 * (regularprice - saleprice) / regularprice;
        int integerValue = (int) Math.round(off);
        return integerValue + "%";
    }

    public static void directLinkCustomTab(Activity act, String url) {
        if (!URLUtil.isValidUrl(url)) {
            Toast.makeText(act, "Ops, Cannot open url", Toast.LENGTH_LONG).show();
            return;
        }
        int color = ResourcesCompat.getColor(act.getResources(), android.R.color.holo_red_dark, null);
        int secondaryColor = ResourcesCompat.getColor(act.getResources(), android.R.color.holo_red_dark, null);

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

    public static void rateUs(Activity activity) {
        activity.startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://play.google.com/store/apps/details?id=" + activity.getPackageName())));
    }

    public static void shareContent(Activity activity, String content_url) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, content_url);
        intent.setType("text/plain");
        intent = Intent.createChooser(intent, "Share Via");
        activity.startActivity(intent);
    }

    public static Animation animLeft(Context ctx) {
        return AnimationUtils.loadAnimation(ctx, android.R.anim.slide_in_left);
    }

    public static Animation animShow(Context ctx) {
        return AnimationUtils.loadAnimation(ctx, R.anim.anim_show);
    }

    public static Animation animHide(Context ctx) {
        return AnimationUtils.loadAnimation(ctx, R.anim.anim_hide);
    }

    public static Animation animHideBottom(Context ctx) {
        return AnimationUtils.loadAnimation(ctx, R.anim.anim_hide_bottom);
    }

    public static Animation animPopUp(Context ctx) {
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(500);
        return anim;
    }

    public static void LogE(String msg) {
        Log.e("Log: ", msg);
    }

    public static void LogD(String msg) {
        Log.d("Log: ", msg);
    }

    public static String getDate() {
        Date currentTime = Calendar.getInstance().getTime();
        return new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
    }

//    TODO: Get decimalNumber ##.## format
    public static String getDecimal(int num) {
        return new DecimalFormat("##.##").format(num);
    }

    //    TODO: Get number with comma #,##,### format
    public static String getNumberWithComma(String num) {
        Double numParsed = Double.parseDouble(num);
        @SuppressLint("DefaultLocale")  String numString = String.format("%,.0f", numParsed);

        return String.format(numString);
    }
    //    TODO: Get number with comma #,##,### format
    public static String getNumberWithComma(Double num) {
        @SuppressLint("DefaultLocale")   String numString = String.format("%,.0f", num);
        return String.format(numString);
    }
    //    TODO: Get number with comma #,##,### format
    public static String getNumberWithComma(int num) {
        Double numParsed = Double.parseDouble(String.valueOf(num));
        @SuppressLint("DefaultLocale")  String numString = String.format("%,.0f", numParsed);
        return String.format(numString);
    }
    //    TODO: Get number with comma #,##,### format
    public static String getNumberWithComma(long num) {
        Double numParsed = Double.parseDouble(String.valueOf(num));
        @SuppressLint("DefaultLocale") String numString = String.format("%,.0f", numParsed);
        return String.format(numString);
    }
    //    TODO: Get number with comma #,##,###.00 format
    public static String getNumberWithCommaWithPoint(String num) {
        Double numParsed = Double.parseDouble(String.valueOf(num));
        @SuppressLint("DefaultLocale") String numString = String.format("%,.2f", numParsed);
        return String.format(numString);
    }

}
