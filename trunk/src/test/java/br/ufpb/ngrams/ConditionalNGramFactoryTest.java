package br.ufpb.ngrams;
import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Test;

public class ConditionalNGramFactoryTest {
  @Test
  public void shouldReturnConditionalNGramsForBaseLength2()
  {
    NgramAnalyzer analyzer = new NgramAnalyzer("enaenaenben");
    ConditionalNGramFactory factory = new ConditionalNGramFactory(analyzer);
    Collection<ConditionalNGram> conditionalNGrams = factory.getConditionalNGrams(2, createDummyProgressListener());
    boolean conditionalNGramForEnPresent = false;
    for (ConditionalNGram conditionalNGram : conditionalNGrams)
    {
      if (conditionalNGram.getBaseNGram().equals("en") && conditionalNGram.getAppendedCharacters().equals("a"))
      {
        assertEquals("Wrong probability.", 0.5f, conditionalNGram.getProbability(), 0.01);
        conditionalNGramForEnPresent = true;
      }
    }
    
    assertTrue("Conditional ngram for en is missing.", conditionalNGramForEnPresent);
  }
  
  private ProgressListener createDummyProgressListener()
  {
    ProgressListener progressListener = createNiceMock(ProgressListener.class);
    replay(progressListener);
    return progressListener;
    
  }
}
