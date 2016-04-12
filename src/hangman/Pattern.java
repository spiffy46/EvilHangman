package hangman;

public class Pattern {
		String pattern;
		int filled;
	public Pattern()
	{
		pattern = "";
		filled = 0;
	}

	public Pattern(int l)
	{
		pattern = "";
		filled = 0;
		for(int i = 0; i < l; i++)
		{
			pattern = pattern + '-';
		}
	}
	
	public Pattern(String s, char g)
	{
		pattern = "";
		filled = 0;
		for(int i = 0; i < s.length(); i++)
		{
			if(s.charAt(i) == g)
			{
				pattern = pattern + g;
				filled++;
			}
			else
				pattern = pattern + '-';
		}
	}
	
	@Override
	public int hashCode() {
		int t = 0;
		for(int i = 0; i < pattern.length(); i++)
		{
			if(pattern.charAt(i) != '-')
			{
				t = (int) (t + Math.pow(2.0, (pattern.length() - i)));
			}
		}
		return t;
	}
	
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pattern other = (Pattern) obj;
		if (filled != other.filled)
			return false;
		if (pattern == null) {
			if (other.pattern != null)
				return false;
		} else if (!pattern.equals(other.pattern))
			return false;
		return true;
	}

	public String toString(){
		return pattern;
	}
	
}
