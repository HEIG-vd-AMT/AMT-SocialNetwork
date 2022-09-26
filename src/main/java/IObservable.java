import java.util.List;

public interface IObservable {
    //Subscribe an Observer to an Observable
    void subscribe(List<IObserver> observer);

    //Unsubscribe an Observer from an Observable
    void unsubscribe(IObserver observer);

    //Notify all Observers about a post, an event and any kind of updates
    void notifyObservers();
}
