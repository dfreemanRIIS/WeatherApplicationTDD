package com.example.dfreeman.weatherapplicationtdd.Model;

public class Weather {

    public Location location;
    public final CurrentCondition currentCondition = new CurrentCondition();
    public final Temperature temperature = new Temperature();
    public final Wind wind = new Wind();

    public  class CurrentCondition {
        private String condition;
        private String descr;
        private float pressure;
        private float humidity;

        public String getCondition() {
            return condition;
        }
        public void setCondition(String condition) {
            this.condition = condition;
        }
        public String getDescr() {
            return descr;
        }
        public void setDescr(String descr) {
            this.descr = descr;
        }
        public float getPressure() {
            return pressure;
        }
        public void setPressure(float pressure) {
            this.pressure = pressure;
        }
        public float getHumidity() {
            return humidity;
        }
        public void setHumidity(float humidity) {
            this.humidity = humidity;
        }
    }

    public  class Temperature {
        private float temp;

        public float getTemp() {
            return temp;
        }
        public void setTemp(float temp) {
            this.temp = temp;
        }
    }

    public  class Wind {
        private float speed;
        private float deg;

        public float getSpeed() {
            return speed;
        }
        public void setSpeed(float speed) {
            this.speed = speed;
        }
        public float getDeg() {
            return deg;
        }
        public void setDeg(float deg) {
            this.deg = deg;
        }
    }
}