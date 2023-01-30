package transport;

import exception.SpeedException;

public class LuxurySportsCar extends SportsCar implements DeluxeSofttop {
        private boolean lightIsOn;
        private String rooftype;
        private boolean defoggerIsOn;
    
        public LuxurySportsCar(String name) throws SpeedException {
            super(name);
        }

        public LuxurySportsCar(String name, int speed, int gasoline, boolean engineState) throws SpeedException {
            super(name, speed, gasoline, engineState);
        }

        public boolean isLightOn(){
            return lightIsOn;
        }
        
        public boolean isDefoggerOn(){
            return defoggerIsOn;
        }
        
        @Override
        public void turnDefoggerOn() {
            defoggerIsOn=true;
            
        }

        @Override
        public void turnDefoggerOff() {
            defoggerIsOn=false;
            
        }

        @Override
        public String getRoofMaterial() {
            return rooftype;
        }

        @Override
        public void turnLightOff() {
            lightIsOn=false;
        }

        @Override
        public void turnLightOn() {
            lightIsOn=true;
        }
    
}
