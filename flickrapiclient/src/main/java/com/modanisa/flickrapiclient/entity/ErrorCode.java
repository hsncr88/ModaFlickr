package com.modanisa.flickrapiclient.entity;

/**
 * Created by canavar on 6/19/2016.
 */
public enum ErrorCode {
    TOO_MANY_TAGS(1,"Too many tags in All query"),
    UNKNOWN_USER(2, "Unknown User"),
    PARAMETERLESS_SEARCH_DISABLED(3, "Parameterless searches have been disabled"),
    PERMISSION_DENIED(4, "You don't have permission to view this pool"),
    API_NOT_AVAILABLE(10, "Sorry, the Flickr search API is not currently available."),
    NO_VALID_MACHINE_TAG(11,"No valid machine tags"),
    EXCEED_MAX_ALLOWABLE_MACH_TAG(12,"Exceeded maximum allowable machine tags"),
    ONLY_SEARCH_WITHIN_YOUR_CONTACTS(17,"You can only search within your own contacts"),
    ILLOGICAL_ARGUMENTS(18,"Illogical arguments"),
    INVALID_API_KEY(100,"Invalid API Key"),
    SERVICE_CURRENTLY_NOT_AVAILABLE(105,"Service currently unavailable"),
    WRITE_OPERATION_FAILED(106,"Write operation failed"),
    FORMAT_NOT_FOUND(111,"Format \"xxx\" not found"),
    METHOD_NOT_FOUND(112,"Method \"xxx\" not found"),
    INVALID_SOAP_ENVELOPE(114,"Invalid SOAP envelope"),
    INVALID_XML_RPC_METHOD_CALL(115,"Invalid XML-RPC Method Call"),
    BAD_URL_FOUND(116,"Bad URL found")
    ;


    private int code;
    private String description;

    ErrorCode(int code, String description)
    {
        this.code = code;
        this.description = description;
    }

    public String getDescription()
    {
        return description;
    }

    public int getCode() {
        return code;
    }
}
