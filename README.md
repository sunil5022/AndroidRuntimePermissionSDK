# Run Time Permission for support for all devices 
Simple Permission SDK

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
2) For example:- PermissionHelper.askCameraVideoPermission(this,100). In the consturctor of this method you need to pass context and request code.
3) You should override:
       override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
       
    }
4) In the body of above method you need to call this method:
    PermissionHelper.onRequestPermissionsResult(
            activity = this,
            requestCode = requestCode,
            grantResults = grantResults,
            permissions = permissions,
            listener = this
        )
``   
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

5) And override this all method:
    override fun onPermissionGranted(requestCode: Int) {}
    override fun onPermissionDenied(requestCode: Int) { }
    override fun onPermissionDeniedGotoSettings(requestCode: Int) {}
    override fun shouldShowRequestPermissionRationale(requestCode: Int) {}
    override fun onPermissionDeniedWithNeverAskAgain(requestCode: Int) {}

   





  

