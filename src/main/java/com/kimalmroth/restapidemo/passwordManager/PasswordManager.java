package com.kimalmroth.restapidemo.passwordManager;

import com.password4j.BcryptFunction;
import com.password4j.types.Bcrypt;
import org.springframework.context.annotation.Bean;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

public class PasswordManager {

    BcryptFunction bcrypt = BcryptFunction.getInstance(Bcrypt.Y, 11);

}
