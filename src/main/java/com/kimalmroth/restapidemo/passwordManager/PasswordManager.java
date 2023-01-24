package com.kimalmroth.restapidemo.passwordManager;

import com.password4j.BcryptFunction;
import com.password4j.types.Bcrypt;

public class PasswordManager {

    BcryptFunction bcrypt = BcryptFunction.getInstance(Bcrypt.Y, 11);

}
