package java_interop_sam_gotchas;

public final class Log {
public static void d(String TAG, String message) {
  System.out.println(TAG + ": " + message);
}

public static void main(String[] args) {
  Log.d("LOG", "TEST");
}

}
