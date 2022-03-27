package resources;

public enum APIResources {
    userPath("/api/users/");

    private String resource;

    APIResources(String resource) {
        this.resource = resource;
    }

    public String getResource() {
        return resource;
    }
}
