package Service.models;

/**
 * author: a.savanovich
 * Date: 14.07.13
 * Time: 14:55
 * To change this template use File | Settings | File Templates.
 */
public interface BaseModel {
    long getId();

    /**
     * author: a.savanovich
     * Date: 14.07.13
     * Time: 14:56
     */
    class Profile implements BaseModel {
        private long id;
        private String name;
        private String lastName;

        @Override
        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
