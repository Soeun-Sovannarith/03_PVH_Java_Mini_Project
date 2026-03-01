package org.example;

public class GetProduct {
        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return name;
        }

        public void setUsername(String name) {
            this.name = name;
        }

        public GetProduct(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

