package org.motechproject.mds.domain;

import org.apache.commons.lang.StringUtils;
import org.motechproject.mds.dto.TypeDto;
import org.motechproject.mds.dto.ValidationCriterionDto;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * The <code>ValidationCriterionMapping</code> class represents single validation criterion. This class is
 * related with table in database with the same name.
 */
@PersistenceCapable(identityType = IdentityType.DATASTORE)
public class ValidationCriterionMapping {

    @Persistent(valueStrategy = IdGeneratorStrategy.INCREMENT)
    @PrimaryKey
    private Long id;

    @Persistent
    private String displayName;

    @Persistent
    private TypeValidationMapping validation;

    @Persistent
    private AvailableFieldTypeMapping type;

    @Persistent
    private String value;

    @Persistent
    private boolean enabled;

    public ValidationCriterionMapping(String displayName, TypeValidationMapping validation, AvailableFieldTypeMapping type) {
        this(displayName, "", false, validation, type);
    }

    public ValidationCriterionMapping(String displayName, String value, boolean enabled, TypeValidationMapping validation, AvailableFieldTypeMapping type) {
        this.displayName = displayName;
        this.value = value;
        this.enabled = enabled;
        this.validation = validation;
        this.type = type;
    }

    public ValidationCriterionDto toDto() {
        return new ValidationCriterionDto(displayName, new TypeDto(type.getDisplayName(), type.getDescription(), type.getTypeClass()),
                parseValue(), enabled);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public AvailableFieldTypeMapping getType() {
        return type;
    }

    public void setType(AvailableFieldTypeMapping type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public TypeValidationMapping getValidation() {
        return validation;
    }

    public void setValidation(TypeValidationMapping validation) {
        this.validation = validation;
    }

    public ValidationCriterionMapping copy() {
        return new ValidationCriterionMapping(displayName, value, enabled, null, type);
    }

    private Object parseValue() {
        if (StringUtils.isBlank(value)) {
            return null;
        }

        String typeClass = type.getTypeClass();

        switch (typeClass) {
            case "java.lang.Integer":
                return Integer.valueOf(value);
            case "java.lang.Double":
                return Double.parseDouble(value);
            default:
                return value;
        }
    }
}