# Tools
This is android basic tools for daily use

# check internet connection is working or not
Tools.isNetConnect()

# change system bar color change with string color
Tools.setSystemBarColor(String color , Activity act)

# change system bar color change with int color id
Tools.setSystemBarColor(@ColorRes int color, Activity act)

# System bar set light color
Tools.setSystemBarLight(Activity act)

# Get device name
Tools. getDeviceName()

# Get android version name
Tools.getAndroidVersion()

# Get version code
Tools.getVersionCode( Context ctx) 

# Get version name
Tools.getVersionName( Context ctx)

# Get version name plain text
Tools.getVersionNamePlain( Context ctx)

# Get device id
Tools.getDeviceID( Context ctx )


# Long to date sample
Tools.getFormattedDateSimple(Long dateTime)

# Display image
Tools.displayImage(Context ctx, ImageView imgs, String url)

# 
Tools.displayImageCircle(Context ctx,ImageView img, Bitmap bmp) 

# 
Tools.displayImageCircle(Context ctx,ImageView img, String url)

#
Tools.displayImageCircle(Context ctx,ImageView img, String url, float thumb)

# 
Tools.displayImageThumb( Context ctx,ImageView img, String url, float thumb) 

# 
Tools.clearImageCacheOnBackground(final Context ctx)

# 
Tools. dpToPx(Context ctx, int dp)

#
Tools.directLinkToBrowser(Activity act, String url)

# 
Tools.isEmailValid(String email) 

# 
Tools.tintDrawable(Context ctx, @DrawableRes int drawable, @ColorRes int color) 

# 
Tools.fromHtml(String html)

#
Tools.hideKeyboard( Activity act)

# 
Tools.showAlert(Activity activity, String msg, String positiveBtnText, @ColorRes int tintColor, @DrawableRes int setIcon)

# 
Tools. progressDialog.show(Activity activity)
Tools. progressDialog.cancel()

#
Tools.showAlert(Activity act,String msg, String positiveBtnText)

# 
Tools.showToast(Activity act,String msg) 

# 
Tools.snackBarError(Activity act, View view, String msg)

#
Tools.snackBarSuccess(Activity act, View view, String msg)

# 
Tools.getSalePercentage(String sale_price,String regular_price )

# 
Tools.directLinkCustomTab(Activity act,String url)



