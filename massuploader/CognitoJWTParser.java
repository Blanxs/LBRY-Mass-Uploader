/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package massuploader;

/*
// Copyright 2013-2016 Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0
 */


import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Base64.Decoder;
import org.json.JSONObject;
//import net.aholbrook.paseto.base64.Base64Provider;
import java.io.UnsupportedEncodingException;
import java.security.InvalidParameterException;

/**
 * Utility class for all operations on JWT.
 */
public class CognitoJWTParser {
    private static final int HEADER = 0;
    private static final int PAYLOAD = 1;
    private static final int SIGNATURE = 2;
    private static final int JWT_PARTS = 3;

    /**
     * Returns header for a JWT as a JSON object.
     *
     * @param jwt REQUIRED: valid JSON Web Token as String.
     * @return header as a JSONObject.
     */
    static JSONObject getHeader(String jwt) {
        try {
            validateJWT(jwt);
            Base64.Decoder dec= Base64.getDecoder();
            final byte[] sectionDecoded = dec.decode(jwt.split("\\.")[HEADER]);
            final String jwtSection = new String(sectionDecoded, "UTF-8");
            return new JSONObject(jwtSection);
        } catch (final UnsupportedEncodingException e) {
            throw new InvalidParameterException(e.getMessage());
        } catch (final Exception e) {
            throw new InvalidParameterException("error in parsing JSON");
        }
    }

    /**
     * Returns payload of a JWT as a JSON object.
     *
     * @param jwt REQUIRED: valid JSON Web Token as String.
     * @return payload as a JSONObject.
     */
    static JSONObject getPayload(String jwt) {
        try {
            validateJWT(jwt);
            Base64.Decoder dec= Base64.getDecoder();
            final String payload = jwt.split("\\.")[PAYLOAD];
            final byte[] sectionDecoded = dec.decode(payload);
            final String jwtSection = new String(sectionDecoded, "UTF-8");
            return new JSONObject(jwtSection);
        } catch (final UnsupportedEncodingException e) {
            throw new InvalidParameterException(e.getMessage());
        } catch (final Exception e) {
            throw new InvalidParameterException("error in parsing JSON");
        }
    }

    /**
     * Returns signature of a JWT as a String.
     *
     * @param jwt REQUIRED: valid JSON Web Token as String.
     * @return signature as a String.
     */
    public static String getSignature(String jwt) {
        try {
            validateJWT(jwt);
            Base64.Decoder dec= Base64.getDecoder();
            final byte[] sectionDecoded = dec.decode(jwt.split("\\.")[SIGNATURE]);
            return new String(sectionDecoded, "UTF-8");
        } catch (final Exception e) {
            throw new InvalidParameterException("error in parsing JSON");
        }
    }

    /**
     * Returns a claim, from the {@code JWT}s' payload, as a String.
     *
     * @param jwt   REQUIRED: valid JSON Web Token as String.
     * @param claim REQUIRED: claim name as String.
     * @return claim from the JWT as a String.
     */
    static String getClaim(String jwt, String claim) {
        try {
            final JSONObject payload = getPayload(jwt);
            final Object claimValue = payload.get(claim);

            if (claimValue != null) {
                return claimValue.toString();
            }

        } catch (final Exception e) {
            throw new InvalidParameterException("invalid token");
        }
        return null;
    }

    /**
     * Checks if {@code JWT} is a valid JSON Web Token.
     *
     * @param jwt REQUIRED: The JWT as a {@link String}.
     */
    static void validateJWT(String jwt) {
        // Check if the the JWT has the three parts
        final String[] jwtParts = jwt.split("\\.");
        if (jwtParts.length != JWT_PARTS) {
            throw new InvalidParameterException("not a JSON Web Token");
        }
    }
}
