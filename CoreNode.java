//this class kinda acts as a basis for classes inheriting from it. 
import java.util.List;

public class CoreNode {
    //make the instance variables protected so that the following classes can inherite from it 
    protected int tokenPosition; //keeps track of the number of tokens that have been "processed"
    protected int tokenPositionProcessed;
    protected List<Lex.Token> tokens; //list of tokens will be shared for all child classes

    public CoreNode (List<Lex.Token> tokens, int tokenPosition) {
        this.tokenPosition = tokenPosition;
        this.tokenPositionProcessed = 0;
        this.tokens = tokens;
    }

}