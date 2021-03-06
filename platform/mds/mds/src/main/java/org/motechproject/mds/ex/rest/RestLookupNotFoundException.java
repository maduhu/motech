package org.motechproject.mds.ex.rest;

import org.motechproject.mds.ex.MdsException;

/**
 * Signals that the lookup requested by REST does not exist.
 */
public class RestLookupNotFoundException extends MdsException {

    private static final long serialVersionUID = 5777555133575169106L;

    /**
     * @param lookupName name of the lookup
     */
    public RestLookupNotFoundException(String lookupName) {
        super(String.format("Lookup \'%s\' not found", lookupName));
    }
}
