import java.util.Date;
import java.util.Random;
import java.util.Vector;

public class Pairs_of_words {
	public Pairs_of_words(Vector<String> list)
	{
		_withRepeat=true;
		leftWords= new Vector<String>();
		rightWords=new Vector<String>();
		for(int i=0;i<list.size();i++)
		{
			String[] words=list.get(i).split("-");
			if(words.length<2)
				continue;
			leftWords.add(words[0].replace(" ", "").toLowerCase());
			rightWords.add(words[1].replace(" ", "").toLowerCase());
		}
	}
	
	public void setMode(boolean withRepeat)
	{
		_withRepeat=withRepeat;
		reStart();
	}
	
	public void reStart()
	{
		if(!_withRepeat)
		{
			leftWords_1=new Vector<String>();
			rightWords_1=new Vector<String>();
			
			for(int i=0;i<leftWords.size();i++)
			{
				leftWords_1.add(leftWords.get(i));
				rightWords_1.add(rightWords.get(i));
			}
		}
	}
	
	public Question Take_Random()
	{
		Question q=new Question();
		if(_withRepeat)
		{
			if(leftWords.size()==0)
				return q;
			Date d=new Date();
			
			Random r=new Random();
			int num= r.nextInt(leftWords.size());
			boolean b=r.nextBoolean();
			if(b)
			{
				q.question=leftWords.get(num);
				q.answer=rightWords.get(num);
			}
			else
			{
				q.answer=leftWords.get(num);
				q.question=rightWords.get(num);
			}
		}
		else
		{
			if(leftWords_1.size()==0)
				return q;
			Random r=new Random();
			int num= r.nextInt(leftWords_1.size());
			boolean b=r.nextBoolean();
			if(b)
			{
				q.question=leftWords_1.get(num);
				q.answer=rightWords_1.get(num);
			}
			else
			{
				q.answer=leftWords_1.get(num);
				q.question=rightWords_1.get(num);
			}			
			leftWords_1.remove(num);
			rightWords_1.remove(num);
		}
		return q;
	}
	
	public class Question
	{
		public String question;
		public String answer;
	}

	Vector<String> leftWords;
	Vector<String> rightWords;
	
	Vector<String> leftWords_1;
	Vector<String> rightWords_1;
	
	boolean _withRepeat;
}
