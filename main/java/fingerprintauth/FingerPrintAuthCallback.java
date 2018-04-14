package fingerprintauth;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;

/**
 * Created by kishori on 12/4/18.
 */

public interface FingerPrintAuthCallback {

    void onNoFingerPrintHardwareFound();

    void onNoFingerPrintRegistered();


    void onBelowMarshmallow();


    void onAuthSuccess(FingerprintManager.CryptoObject cryptoObject);

    void onAuthFailed(int errorCode, String errorMessage);
}
