import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TestTwitter {
    //region private attributes
    private Twitter _twitter;
    //endregion private attributes

    @BeforeEach
    private void beforeEach(){
        _twitter = new Twitter();
    }

    @Test
    public void observers_AfterInstantiationWithoutObservers_Success(){
        //given
        //refer to Before Each method
        int expectedAmountOfObservers = 0;

        //when
        //event is called directly by the assertion

        //then
        assertEquals(expectedAmountOfObservers, _twitter.getObservers().size());
    }

    @Test
    public void observers_AfterInstantiationWithObservers_Success(){
        //given
        //refer to Before Each method
        int expectedAmountOfObservers = 10;
        _twitter = new Twitter(generateObserver(expectedAmountOfObservers));

        //when
        //event is called directly by the assertion

        //then
        assertEquals(expectedAmountOfObservers, _twitter.getObservers().size());
    }

    @Test
    public void twits_AfterInstantiation_Success(){
        //given
        //refer to Before Each method
        int expectedAmountOfTwits = 0;

        //when
        //event is called directly by the assertion

        //then
        assertEquals(expectedAmountOfTwits, _twitter.getTwits().size());
    }

    @Test
    public void notifySubscribers_EmptyListOfSubscriber_ThrowsException(){
        //given
        //refer to Before Each method

        //when
        //event is called directly by the assertion

        //then
        assertThrows(Twitter.EmptyListOfSubscribersException.class, () -> {
            _twitter.notifyObservers();
        });
    }

    @Test
    public void subscribe_AddFirstSubscriber_Success() {
        //given
        //refer to Before Each method
        int expectedAmountOfSubscribers = 15;
        List<IObserver> followers = generateObserver(expectedAmountOfSubscribers);

        //when
        _twitter.subscribe(followers);

        //then
        assertEquals(expectedAmountOfSubscribers, _twitter.getObservers().size());
    }

    @Test
    public void subscribe_AddSubscribersToExistingList_Success(){
        //given
        //refer to Before Each method
        int expectedAmountOfSubscriber = 30;
        List<IObserver> followers = generateObserver(expectedAmountOfSubscriber / 2);
        _twitter.subscribe(followers);
        List<IObserver> followersToAdd = generateObserver(expectedAmountOfSubscriber / 2);

        //when
        _twitter.subscribe(followersToAdd);

        //then
        assertEquals(expectedAmountOfSubscriber, _twitter.getObservers().size());
    }

    @Test
    public void subscribe_SubscriberAlreadyExists_ThrowsException()
    {
        //given
        //refer to Before Each method
        int expectedAmountOfSubscriber = 15;
        List<IObserver> followers = generateObserver(expectedAmountOfSubscriber);
        _twitter.subscribe(followers);
        List<IObserver> followersDuplicate = new ArrayList<IObserver>();
        followersDuplicate.add(followers.get(0));

        //when
        //event is called directly by the assertion

        //then
        assertThrows(Twitter.SubscriberAlreadyExistsException.class, () -> {
            _twitter.subscribe(followersDuplicate);
        });
    }

    @Test
    public void unsubscribe_NominalCase_Success()
    {
        //given
        //refer to Before Each method
        int expectedAmountOfSubscribers = 14;
        List<IObserver> followers = generateObserver(expectedAmountOfSubscribers + 1);
        _twitter.subscribe(followers);

        //when
        _twitter.unsubscribe(followers.get(0));

        //then
        assertEquals(expectedAmountOfSubscribers, _twitter.getObservers().size());
    }

    @Test
    public void unsubscribe_EmptyListOfSubscriber_ThrowsException()
    {
        //given
        //refer to Before Each method
        Follower followerToRemove = new Follower();

        //when
        //event is called directly by the assertion

        //then
        assertThrows(Twitter.EmptyListOfSubscribersException.class, () -> {
            _twitter.unsubscribe(followerToRemove);
        });
    }

    @Test
    public void unsubscribe_SubscriberNotFound_ThrowsException()
    {
        //given
        //refer to Before Each method
        IObserver followerNotFound = new Follower();
        _twitter.subscribe(generateObserver(10));

        //when
        //event is called directly by the assertion

        //then
        assertThrows(Twitter.SubscriberNotFoundException.class, () -> {
            _twitter.unsubscribe(followerNotFound);
        });
    }


    //endregion public methods

    //region private methods
    private List<IObserver> generateObserver(int amountOfObserverToCreate){
        List<IObserver> observers = new ArrayList<IObserver>();
        for(int i = 0 ; i < amountOfObserverToCreate; i++){
            observers.add(new Follower());
        }
        return observers;
    }
    //endregion private methods
}
