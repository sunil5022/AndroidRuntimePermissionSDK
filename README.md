# Run Time Permission for support for all devices with pick the media file form gallery and fetch the real path of it
Simple Permission SDK with Media handling

> Step 1. Add the JitPack repository to your build file

```gradle
allprojects {
    repositories {
      ...
      maven { url 'https://jitpack.io' }
		}
 }
```
> Step 2. Add the dependency
```gradle
  dependencies {
	        implementation 'com.github.sunil5022:AndroidRuntimePermissionSDK:1.0.0'
}
```

# HOW TO USE..
1) Call this class "PermissionHelper" for ask any permission.
2) For example:- PermissionHelper.askCameraVideoPermission(this,100). Pass context and request code as a parameter in this method.
3) You should override:
```   
       override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
       
    }
```
4) In the body of above method you need to call this method:
```
    PermissionHelper.onRequestPermissionsResult(
            activity = this,
            requestCode = requestCode,
            grantResults = grantResults,
            permissions = permissions,
            listener = this
        )   
# For Example:
  override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        PermissionHelper.onRequestPermissionsResult(
            activity = this,
            requestCode = requestCode,
            grantResults = grantResults,
            permissions = permissions,
            listener = this
        )
    }
```
5) And override this all method:
```
    override fun onPermissionGranted(requestCode: Int) {}
    override fun onPermissionDenied(requestCode: Int) { }
    override fun onPermissionDeniedGotoSettings(requestCode: Int) {}
    override fun shouldShowRequestPermissionRationale(requestCode: Int) {}
    override fun onPermissionDeniedWithNeverAskAgain(requestCode: Int) {}

```
# FOR PICK MEDIA FROM GALLLERY:
> Step 1. Call Following method inside click of button:
```
  - For Only Image : OpenGallery.forImage(requestCode, this, "Pick Image") // requestCode will be your given any number
  - For Only Video : OpenGallery.forVideo(requestCode, this, "Pick Video") // requestCode will be your given any number
  - For Only Video : OpenGallery.forImageAndVideo(requestCode, this, "Pick Image Video") // requestCode will be your given any number.
```
> Step 2. For Get the real path of selected media:

Should overRide the onActivityResult method and call this methos inside it.
```
override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {

            val selectedMediaUri: Uri? = data?.data
            Log.d("onActivityResult selectedMediaUri : ", selectedMediaUri.toString())

           val realPath =selectedMediaUri?.let { GetRealPath.getRealPathFromURI(this, it) }
           Log.d("onActivityResult realMediaPath : ", realPath!!)
        }
    }
