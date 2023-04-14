
package com.example.dogparkviewmodel;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

public class DogParkViewModel extends ViewModel {


    class DogsInfo{
        int id,size;

        public DogsInfo(int id, int size) {
            this.id = id;
            this.size = size;
        }

        public int getId() {
            return id;
        }

        public int getSize() {
            return size;
        }
    }

    private MutableLiveData<Integer> allEntries=new MutableLiveData<>();
    private MutableLiveData<Integer> allExits=new MutableLiveData<>();
    private MutableLiveData<Integer> max=new MutableLiveData<>();
    private MutableLiveData<Integer> inPark=new MutableLiveData<>();
    private MutableLiveData<Integer> HumansInPark=new MutableLiveData<>();

    private LiveData<String> allEntriesString = Transformations.map(allEntries, new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
            return "Total Entries " + input ;
        }
    });

    private LiveData<String> allExitsString = Transformations.map(allExits, new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
            return "Total Exits " + input ;
        }
    });

    private LiveData<String> inParkString = Transformations.map(inPark, new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
            return "Dogs In Park Currently " + input ;
        }
    });

    private LiveData<String> HumansInParkString = Transformations.map(HumansInPark, new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
            return "Humans In Park Currently " + input ;
        }
    });

    public DogParkViewModel(){
        reset();
    }

    public boolean 	isFull(){
        if((inPark.getValue())>=( max.getValue())){
            return true;
        }
        return false;
    }

    public boolean 	isEmpty(){
        if(inPark.getValue()<=0){
            return true;
        }
        return false;
    }

    public boolean changeMax(int newMax){
        if(newMax>0){
            reset();
            max.setValue(newMax);
            return true;
        }
        return false;
    }

    public boolean doubleEntry(){
        int currAllEntries=allEntries.getValue()+2;
        allEntries.setValue(currAllEntries);
        boolean maxcheck=isFull();
        if(!maxcheck){
            int currInPark=inPark.getValue()+2;
            inPark.setValue(currInPark);
            return false;
        }
        return true;
    }

    public boolean changeDogsInPark(int dogsInPark){
        int maxVal= max.getValue();
        if(dogsInPark>maxVal){
            return false;
        }
        inPark.setValue(dogsInPark);
        return true;
    }

    public boolean changeHumans(int newhumans){
        int dogsval=inPark.getValue();
        if (newhumans>=dogsval){
            HumansInPark.setValue(newhumans);
            return true;
        }
        return false;
    }

    public boolean enter(){
        int currAllEntries=allEntries.getValue()+1;
        allEntries.setValue(currAllEntries);
        boolean maxcheck=isFull();
        if(!maxcheck){
            int currInPark=inPark.getValue()+1;
            inPark.setValue(currInPark);
            return false;
        }
        return true;
    }

    public boolean exit(){
        int currAllEntries=allExits.getValue()+1;
        allExits.setValue(currAllEntries);
        boolean mincheck=isEmpty();
        if(!mincheck){
            int currentInPark=inPark.getValue()-1;
            inPark.setValue(currentInPark);
            return false;
        }
        return true;
    }

    public void reset(){
        max.setValue(10);
        allEntries.setValue(0);
        allExits.setValue(0);
        inPark.setValue(0);
        HumansInPark.setValue(0);
    }

    public LiveData<String> getInParkString(){
        return inParkString;
    }

    public LiveData<String> getAllEntriesString(){
        return allEntriesString;
    }

    public LiveData<String> getAllExitsString(){
        return allExitsString;
    }
    public LiveData<String> getAllHumansString(){
        return HumansInParkString;
    }

}