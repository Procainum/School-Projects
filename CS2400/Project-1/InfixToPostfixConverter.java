import java.util.Stack;

public class InfixToPostfixConverter implements ExpressionConverterInterface
{
    private StackInterface<Character> infixStack;
    private String postfixEpxression;

    public InfixToPostfixConverter() //Default Constructor
    {
        this(new LinkedStack<Character>());
    }

    public InfixToPostfixConverter(StackInterface<Character> stack) //Constructor
    {
         infixStack = new stack<Character>();
    }

    public String convert(String expression) throws InvalidExpressionException
    {
        return convertToPostfix(expression);
    }

    public String  convertToPostfix(String infix)
    {
        //StackInterface<Character> operatorStack = new Stack;
        String processedInfix = infix.replaceAll("\\s",""); //Found online, removes all whitespace from string
        String postfix = ""; //String to be filled
        int index = 0;
        while(index < processedInfix.length())
        {
            char nextCharacter = processedInfix.charAt(index); //next character
            ++index; //Prepares the index for next time in loop
            switch(nextCharacter)
            {
                case
            }
        }
    }
}

/*
Notes:
expressionconverterinterface = new prefixtopostfixconverter
eeds internal stack to process algo, inside pricvate stack of type stackinterface<char>
1 default constructor,
const: public PTPC() {}
public PTPC(stackinterface<Character> stack)
 */