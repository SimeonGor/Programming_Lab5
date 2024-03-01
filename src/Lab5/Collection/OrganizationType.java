package Lab5.Collection;

import Lab5.Exceptions.InvalidArgument;

import java.util.ArrayList;

public enum OrganizationType {
    COMMERCIAL {
        @Override
        public String toString() {
            return "commercial";
        }
    },
    PUBLIC {
        @Override
        public String toString() {
            return "public";
        }
    },
    PRIVATE_LIMITED_COMPANY {
        @Override
        public String toString() {
            return "PLC";
        }
    },
    OPEN_JOINT_STOCK_COMPANY {
        @Override
        public String toString() {
            return "OJSC";
        }
    };

    public static String[] listOfElements() {
        ArrayList<String> result = new ArrayList<>();
        for (var e : values()) {
            result.add(e.toString());
        }

        return result.toArray(new String[0]);
    }

    public static String listOfElementsPrettyView() {
        StringBuilder result = new StringBuilder();
        result.append("{ ");
        for (var e : listOfElements()) {
            result.append(e).append(", ");
        }
        result.delete(result.length() - 2, result.length()).append(" }");
        return result.toString();
    }

    public static OrganizationType getByName(String name) throws InvalidArgument {
        for (var e : values()) {
            if (e.toString().equals(name) || e.name().equals(name)) {
                return e;
            }
        }
        throw new InvalidArgument(name + " is not in " + listOfElementsPrettyView());
    }
}