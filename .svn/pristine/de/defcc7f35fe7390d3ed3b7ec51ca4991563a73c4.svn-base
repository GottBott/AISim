// Generated from Ctaems.g4 by ANTLR 4.2.2
package edu.udel.cisc475.aisim.input;import java.util.StringTokenizer;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CtaemsParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		AgentToken=1, VersionToken=2, TaskGroupToken=3, TaskToken=4, MethodToken=5, 
		EnablesToken=6, FacilitatesToken=7, DisablesToken=8, HindersToken=9, Spaces=10, 
		SpliceToken=11, TimeToken=12;
	public static final String[] tokenNames = {
		"<INVALID>", "AgentToken", "VersionToken", "TaskGroupToken", "TaskToken", 
		"MethodToken", "EnablesToken", "FacilitatesToken", "DisablesToken", "HindersToken", 
		"Spaces", "SpliceToken", "TimeToken"
	};
	public static final int
		RULE_labelParse = 0, RULE_agentParse = 1, RULE_densityParse = 2, RULE_qualityDistributionParse = 3, 
		RULE_durationDistributionParse = 4, RULE_versionParse = 5, RULE_earliestStartTimeParse = 6, 
		RULE_deadlineParse = 7, RULE_subtaskParse = 8, RULE_qafParse = 9, RULE_fromParse = 10, 
		RULE_toParse = 11, RULE_qualityPowerParse = 12, RULE_durationPowerParse = 13, 
		RULE_spliceParse = 14, RULE_timeParse = 15, RULE_parse = 16;
	public static final String[] ruleNames = {
		"labelParse", "agentParse", "densityParse", "qualityDistributionParse", 
		"durationDistributionParse", "versionParse", "earliestStartTimeParse", 
		"deadlineParse", "subtaskParse", "qafParse", "fromParse", "toParse", "qualityPowerParse", 
		"durationPowerParse", "spliceParse", "timeParse", "parse"
	};

	@Override
	public String getGrammarFileName() { return "Ctaems.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	String token = "notDone"; 
			 String labelParseString = "notDone";
			 String agentParseString = "notDone";
			 String densityParseString = "notDone";
			 String versionParseString = "notDone";
			 String earliestStartTimeParseString = "notDone";
			 String deadlineParseString = "notDone";
			 String qafParseString = "notDone";
			 String toParseString = "notDone";
			 String fromParseString = "notDone";
			 String parseType = "notNew";
			 int timeParseInt = 0;
			 String spliceString = "notDone";	 
			 boolean hasEST = false;
			 boolean hasDeadline = false;
			 double[] qualityDistributionArray;
			 double[] durationDistributionArray;
			 double[] qualityPowerArray;
			 double[] durationPowerArray;
			 String[] subtaskArray;

	public CtaemsParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class LabelParseContext extends ParserRuleContext {
		public Token AgentToken;
		public Token EnablesToken;
		public Token DisablesToken;
		public Token FacilitatesToken;
		public Token HindersToken;
		public Token TaskToken;
		public Token TaskGroupToken;
		public Token MethodToken;
		public TerminalNode MethodToken() { return getToken(CtaemsParser.MethodToken, 0); }
		public TerminalNode AgentToken() { return getToken(CtaemsParser.AgentToken, 0); }
		public TerminalNode TaskGroupToken() { return getToken(CtaemsParser.TaskGroupToken, 0); }
		public TerminalNode FacilitatesToken() { return getToken(CtaemsParser.FacilitatesToken, 0); }
		public TerminalNode TaskToken() { return getToken(CtaemsParser.TaskToken, 0); }
		public TerminalNode DisablesToken() { return getToken(CtaemsParser.DisablesToken, 0); }
		public TerminalNode HindersToken() { return getToken(CtaemsParser.HindersToken, 0); }
		public TerminalNode EnablesToken() { return getToken(CtaemsParser.EnablesToken, 0); }
		public LabelParseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_labelParse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CtaemsListener ) ((CtaemsListener)listener).enterLabelParse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CtaemsListener ) ((CtaemsListener)listener).exitLabelParse(this);
		}
	}

	public final LabelParseContext labelParse() throws RecognitionException {
		LabelParseContext _localctx = new LabelParseContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_labelParse);
		try {
			setState(50);
			switch (_input.LA(1)) {
			case AgentToken:
				enterOuterAlt(_localctx, 1);
				{
				setState(34); ((LabelParseContext)_localctx).AgentToken = match(AgentToken);
				labelParseString = (((LabelParseContext)_localctx).AgentToken!=null?((LabelParseContext)_localctx).AgentToken.getText():null);
				    			if(labelParseString.contains("(label)")){labelParseString = "ERROR";}
				    			else{
				  					int endIndex = labelParseString.indexOf(')'); 
				  					labelParseString = labelParseString.substring(21,endIndex);}
				  				
				}
				break;
			case EnablesToken:
				enterOuterAlt(_localctx, 2);
				{
				setState(36); ((LabelParseContext)_localctx).EnablesToken = match(EnablesToken);
				labelParseString = (((LabelParseContext)_localctx).EnablesToken!=null?((LabelParseContext)_localctx).EnablesToken.getText():null);
				  				  if(labelParseString.contains("(label)")){labelParseString = "ERROR";}
				  				  else{
				  				  labelParseString = labelParseString.substring(1);
				  				  int startIndex = labelParseString.indexOf('(');
				  				  int endIndex = labelParseString.indexOf(')');
				  				  labelParseString = labelParseString.substring(startIndex,endIndex);
				  				  startIndex = labelParseString.indexOf(' ')+1;
				  				  labelParseString = labelParseString.substring(startIndex);}
				}
				break;
			case DisablesToken:
				enterOuterAlt(_localctx, 3);
				{
				setState(38); ((LabelParseContext)_localctx).DisablesToken = match(DisablesToken);
				labelParseString = (((LabelParseContext)_localctx).DisablesToken!=null?((LabelParseContext)_localctx).DisablesToken.getText():null);
				  				  if(labelParseString.contains("(label)")){labelParseString = "ERROR";}
				  				  else{
				  				  labelParseString = labelParseString.substring(1);
				  				  int startIndex = labelParseString.indexOf('(');
				  				  int endIndex = labelParseString.indexOf(')');
				  				  labelParseString = labelParseString.substring(startIndex,endIndex);
				  				  startIndex = labelParseString.indexOf(' ')+1;
				  				  labelParseString = labelParseString.substring(startIndex);}
				}
				break;
			case FacilitatesToken:
				enterOuterAlt(_localctx, 4);
				{
				setState(40); ((LabelParseContext)_localctx).FacilitatesToken = match(FacilitatesToken);
				labelParseString = (((LabelParseContext)_localctx).FacilitatesToken!=null?((LabelParseContext)_localctx).FacilitatesToken.getText():null);
				  				  if(labelParseString.contains("(label)")){labelParseString = "ERROR";}
				  				  else{
				  				  labelParseString = labelParseString.substring(1);
				  				  int startIndex = labelParseString.indexOf('(');
				  				  int endIndex = labelParseString.indexOf(')');
				  				  labelParseString = labelParseString.substring(startIndex,endIndex);
				  				  startIndex = labelParseString.indexOf(' ')+1;
				  				  labelParseString = labelParseString.substring(startIndex);}
				}
				break;
			case HindersToken:
				enterOuterAlt(_localctx, 5);
				{
				setState(42); ((LabelParseContext)_localctx).HindersToken = match(HindersToken);
				labelParseString = (((LabelParseContext)_localctx).HindersToken!=null?((LabelParseContext)_localctx).HindersToken.getText():null);
				  				  if(labelParseString.contains("(label)")){labelParseString = "ERROR";}
				  				  else{
				  				  labelParseString = labelParseString.substring(1);
				  				  int startIndex = labelParseString.indexOf('(');
				  				  int endIndex = labelParseString.indexOf(')');
				  				  labelParseString = labelParseString.substring(startIndex,endIndex);
				  				  startIndex = labelParseString.indexOf(' ')+1;
				  				  labelParseString = labelParseString.substring(startIndex);}
				}
				break;
			case TaskToken:
				enterOuterAlt(_localctx, 6);
				{
				setState(44); ((LabelParseContext)_localctx).TaskToken = match(TaskToken);
				labelParseString = (((LabelParseContext)_localctx).TaskToken!=null?((LabelParseContext)_localctx).TaskToken.getText():null);
				  				  if(labelParseString.contains("(label)")){labelParseString = "ERROR";}
				  				  else{
				  				  labelParseString = labelParseString.substring(1);
				  				  int startIndex = labelParseString.indexOf('(');
				  				  int endIndex = labelParseString.indexOf(')');
				  				  labelParseString = labelParseString.substring(startIndex,endIndex);
				  				  startIndex = labelParseString.indexOf(' ')+1;
				  				  labelParseString = labelParseString.substring(startIndex);}
				}
				break;
			case TaskGroupToken:
				enterOuterAlt(_localctx, 7);
				{
				setState(46); ((LabelParseContext)_localctx).TaskGroupToken = match(TaskGroupToken);
				labelParseString = (((LabelParseContext)_localctx).TaskGroupToken!=null?((LabelParseContext)_localctx).TaskGroupToken.getText():null);
				  				  if(labelParseString.contains("(label)")){labelParseString = "ERROR";}
				  				  else{
				  				  labelParseString = labelParseString.substring(1);
				  				  int startIndex = labelParseString.indexOf('(');
				  				  int endIndex = labelParseString.indexOf(')');
				  				  labelParseString = labelParseString.substring(startIndex,endIndex);
				  				  startIndex = labelParseString.indexOf(' ')+1;
				  				  labelParseString = labelParseString.substring(startIndex);}
				}
				break;
			case MethodToken:
				enterOuterAlt(_localctx, 8);
				{
				setState(48); ((LabelParseContext)_localctx).MethodToken = match(MethodToken);
				labelParseString = (((LabelParseContext)_localctx).MethodToken!=null?((LabelParseContext)_localctx).MethodToken.getText():null);
				  				 if(labelParseString.contains("(label)")){labelParseString = "ERROR";}
				  				 else{
				  				 labelParseString = labelParseString.substring(1);
				  				 int startIndex = labelParseString.indexOf('(');
				  				 int endIndex = labelParseString.indexOf(')');
				  				 labelParseString = labelParseString.substring(startIndex,endIndex);
				  				 startIndex = labelParseString.indexOf(' ')+1;
				  				 labelParseString = labelParseString.substring(startIndex);}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AgentParseContext extends ParserRuleContext {
		public Token MethodToken;
		public TerminalNode MethodToken() { return getToken(CtaemsParser.MethodToken, 0); }
		public AgentParseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_agentParse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CtaemsListener ) ((CtaemsListener)listener).enterAgentParse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CtaemsListener ) ((CtaemsListener)listener).exitAgentParse(this);
		}
	}

	public final AgentParseContext agentParse() throws RecognitionException {
		AgentParseContext _localctx = new AgentParseContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_agentParse);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52); ((AgentParseContext)_localctx).MethodToken = match(MethodToken);
			agentParseString = (((AgentParseContext)_localctx).MethodToken!=null?((AgentParseContext)_localctx).MethodToken.getText():null);
							if(agentParseString.contains("(agent)")){agentParseString = "ERROR";}
							else{
					  		 String omitString = "(agent";
							 int startIndex = agentParseString.indexOf(omitString);
							 agentParseString = agentParseString.substring(startIndex);
							 startIndex = omitString.length()+1;		 
							 int endIndex = agentParseString.indexOf(')');
							 agentParseString = agentParseString.substring(startIndex,endIndex);}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DensityParseContext extends ParserRuleContext {
		public Token MethodToken;
		public TerminalNode MethodToken() { return getToken(CtaemsParser.MethodToken, 0); }
		public DensityParseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_densityParse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CtaemsListener ) ((CtaemsListener)listener).enterDensityParse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CtaemsListener ) ((CtaemsListener)listener).exitDensityParse(this);
		}
	}

	public final DensityParseContext densityParse() throws RecognitionException {
		DensityParseContext _localctx = new DensityParseContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_densityParse);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(55); ((DensityParseContext)_localctx).MethodToken = match(MethodToken);
			densityParseString = (((DensityParseContext)_localctx).MethodToken!=null?((DensityParseContext)_localctx).MethodToken.getText():null);
							 String omitString = "(density";				 
							 int startIndex = densityParseString.indexOf(omitString);
							 densityParseString = densityParseString.substring(startIndex);
							 startIndex = omitString.length()+1;		 
							 int endIndex = densityParseString.indexOf(')');
							 densityParseString = densityParseString.substring(startIndex,endIndex);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class QualityDistributionParseContext extends ParserRuleContext {
		public Token MethodToken;
		public TerminalNode MethodToken() { return getToken(CtaemsParser.MethodToken, 0); }
		public QualityDistributionParseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qualityDistributionParse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CtaemsListener ) ((CtaemsListener)listener).enterQualityDistributionParse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CtaemsListener ) ((CtaemsListener)listener).exitQualityDistributionParse(this);
		}
	}

	public final QualityDistributionParseContext qualityDistributionParse() throws RecognitionException {
		QualityDistributionParseContext _localctx = new QualityDistributionParseContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_qualityDistributionParse);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58); ((QualityDistributionParseContext)_localctx).MethodToken = match(MethodToken);
			String qualityParseString = (((QualityDistributionParseContext)_localctx).MethodToken!=null?((QualityDistributionParseContext)_localctx).MethodToken.getText():null);
							 String omitString = "(quality_distribution";				 
							 int startIndex = qualityParseString.indexOf(omitString);
							 qualityParseString = qualityParseString.substring(startIndex);
							 startIndex = omitString.length()+1;		 
							 int endIndex = qualityParseString.indexOf(')');
							 qualityParseString = qualityParseString.substring(startIndex,endIndex);
							 StringTokenizer tokenizer = new StringTokenizer(qualityParseString);
			    			 int size = tokenizer.countTokens();
			                 qualityDistributionArray = new double[size];
			    			 for(int i = 0;i < size;i++)
			        		 	qualityDistributionArray[i] = Double.parseDouble((String)tokenizer.nextElement());
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DurationDistributionParseContext extends ParserRuleContext {
		public Token MethodToken;
		public TerminalNode MethodToken() { return getToken(CtaemsParser.MethodToken, 0); }
		public DurationDistributionParseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_durationDistributionParse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CtaemsListener ) ((CtaemsListener)listener).enterDurationDistributionParse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CtaemsListener ) ((CtaemsListener)listener).exitDurationDistributionParse(this);
		}
	}

	public final DurationDistributionParseContext durationDistributionParse() throws RecognitionException {
		DurationDistributionParseContext _localctx = new DurationDistributionParseContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_durationDistributionParse);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61); ((DurationDistributionParseContext)_localctx).MethodToken = match(MethodToken);
			String durationParseString = (((DurationDistributionParseContext)_localctx).MethodToken!=null?((DurationDistributionParseContext)_localctx).MethodToken.getText():null);
							 String omitString = "(duration_distribution";				 
							 int startIndex = durationParseString.indexOf(omitString);
							 durationParseString = durationParseString.substring(startIndex);
							 startIndex = omitString.length()+1;		 
							 int endIndex = durationParseString.indexOf(')');
							 durationParseString = durationParseString.substring(startIndex,endIndex);
							 StringTokenizer tokenizer = new StringTokenizer(durationParseString);
			    			 int size = tokenizer.countTokens();
			                 durationDistributionArray = new double[size];
			    			 for(int i = 0;i < size;i++)
			        		 	durationDistributionArray[i] = Double.parseDouble((String)tokenizer.nextElement());
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VersionParseContext extends ParserRuleContext {
		public Token VersionToken;
		public TerminalNode VersionToken() { return getToken(CtaemsParser.VersionToken, 0); }
		public VersionParseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_versionParse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CtaemsListener ) ((CtaemsListener)listener).enterVersionParse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CtaemsListener ) ((CtaemsListener)listener).exitVersionParse(this);
		}
	}

	public final VersionParseContext versionParse() throws RecognitionException {
		VersionParseContext _localctx = new VersionParseContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_versionParse);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64); ((VersionParseContext)_localctx).VersionToken = match(VersionToken);
			versionParseString = (((VersionParseContext)_localctx).VersionToken!=null?((VersionParseContext)_localctx).VersionToken.getText():null);
							 String omitString = "(spec_version";
							 int startIndex = omitString.length()+1;		 
							 int endIndex = versionParseString.indexOf(')');
							 versionParseString = versionParseString.substring(startIndex,endIndex);
							 if(versionParseString.charAt(0)=='"' && versionParseString.charAt(versionParseString.length()-1) =='"'){
							 	versionParseString = versionParseString.substring(1,versionParseString.length()-1);
							 }
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EarliestStartTimeParseContext extends ParserRuleContext {
		public Token TaskToken;
		public TerminalNode TaskToken() { return getToken(CtaemsParser.TaskToken, 0); }
		public EarliestStartTimeParseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_earliestStartTimeParse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CtaemsListener ) ((CtaemsListener)listener).enterEarliestStartTimeParse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CtaemsListener ) ((CtaemsListener)listener).exitEarliestStartTimeParse(this);
		}
	}

	public final EarliestStartTimeParseContext earliestStartTimeParse() throws RecognitionException {
		EarliestStartTimeParseContext _localctx = new EarliestStartTimeParseContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_earliestStartTimeParse);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67); ((EarliestStartTimeParseContext)_localctx).TaskToken = match(TaskToken);
			earliestStartTimeParseString = (((EarliestStartTimeParseContext)_localctx).TaskToken!=null?((EarliestStartTimeParseContext)_localctx).TaskToken.getText():null);
								 String omitString = "(earliest_start_time";
								 if((((EarliestStartTimeParseContext)_localctx).TaskToken!=null?((EarliestStartTimeParseContext)_localctx).TaskToken.getText():null).contains(omitString)){				 
								 	int startIndex = earliestStartTimeParseString.indexOf(omitString);
								 	earliestStartTimeParseString = earliestStartTimeParseString.substring(startIndex);
								 	startIndex = omitString.length()+1;		 
								 	int endIndex = earliestStartTimeParseString.indexOf(')');
								 	earliestStartTimeParseString = earliestStartTimeParseString.substring(startIndex,endIndex);
								 	hasEST = true;}
								 else
								 	hasEST = false;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeadlineParseContext extends ParserRuleContext {
		public Token TaskToken;
		public TerminalNode TaskToken() { return getToken(CtaemsParser.TaskToken, 0); }
		public DeadlineParseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_deadlineParse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CtaemsListener ) ((CtaemsListener)listener).enterDeadlineParse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CtaemsListener ) ((CtaemsListener)listener).exitDeadlineParse(this);
		}
	}

	public final DeadlineParseContext deadlineParse() throws RecognitionException {
		DeadlineParseContext _localctx = new DeadlineParseContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_deadlineParse);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70); ((DeadlineParseContext)_localctx).TaskToken = match(TaskToken);
			deadlineParseString = (((DeadlineParseContext)_localctx).TaskToken!=null?((DeadlineParseContext)_localctx).TaskToken.getText():null);
						  String omitString = "(deadline";
						  if((((DeadlineParseContext)_localctx).TaskToken!=null?((DeadlineParseContext)_localctx).TaskToken.getText():null).contains(omitString)){				 
				   	 	  	int startIndex = deadlineParseString.indexOf(omitString);
							deadlineParseString = deadlineParseString.substring(startIndex);
							startIndex = omitString.length()+1;		 
							int endIndex = deadlineParseString.indexOf(')');
							deadlineParseString = deadlineParseString.substring(startIndex,endIndex);
							hasDeadline = true;}
						  else
							hasDeadline = false;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SubtaskParseContext extends ParserRuleContext {
		public Token TaskToken;
		public Token TaskGroupToken;
		public Token SpliceToken;
		public TerminalNode TaskGroupToken() { return getToken(CtaemsParser.TaskGroupToken, 0); }
		public TerminalNode TaskToken() { return getToken(CtaemsParser.TaskToken, 0); }
		public TerminalNode SpliceToken() { return getToken(CtaemsParser.SpliceToken, 0); }
		public SubtaskParseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subtaskParse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CtaemsListener ) ((CtaemsListener)listener).enterSubtaskParse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CtaemsListener ) ((CtaemsListener)listener).exitSubtaskParse(this);
		}
	}

	public final SubtaskParseContext subtaskParse() throws RecognitionException {
		SubtaskParseContext _localctx = new SubtaskParseContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_subtaskParse);
		try {
			setState(79);
			switch (_input.LA(1)) {
			case TaskToken:
				enterOuterAlt(_localctx, 1);
				{
				setState(73); ((SubtaskParseContext)_localctx).TaskToken = match(TaskToken);
				String subtaskParseString = (((SubtaskParseContext)_localctx).TaskToken!=null?((SubtaskParseContext)_localctx).TaskToken.getText():null);
							  String omitString = "(subtasks";				 
					   	 	  int startIndex = subtaskParseString.indexOf(omitString);
							  subtaskParseString = subtaskParseString.substring(startIndex);
							  startIndex = omitString.length()+1;		 
							  int endIndex = subtaskParseString.indexOf(')');
							  subtaskParseString = subtaskParseString.substring(startIndex,endIndex);
							  StringTokenizer tokenizer = new StringTokenizer(subtaskParseString);
				    		  int size = tokenizer.countTokens();
				              subtaskArray = new String[size];
				    	      for(int i = 0;i < size;i++)
				        	  	 subtaskArray[i] = (String)tokenizer.nextElement();
				}
				break;
			case TaskGroupToken:
				enterOuterAlt(_localctx, 2);
				{
				setState(75); ((SubtaskParseContext)_localctx).TaskGroupToken = match(TaskGroupToken);
				String subtaskParseString = (((SubtaskParseContext)_localctx).TaskGroupToken!=null?((SubtaskParseContext)_localctx).TaskGroupToken.getText():null);
							  String omitString = "(subtasks";				 
					   	 	  int startIndex = subtaskParseString.indexOf(omitString);
							  subtaskParseString = subtaskParseString.substring(startIndex);
							  startIndex = omitString.length()+1;		 
							  int endIndex = subtaskParseString.indexOf(')');
							  subtaskParseString = subtaskParseString.substring(startIndex,endIndex);
							  StringTokenizer tokenizer = new StringTokenizer(subtaskParseString);
				    		  int size = tokenizer.countTokens();
				              subtaskArray = new String[size];
				    	      for(int i = 0;i < size;i++)
				        	  	 subtaskArray[i] = (String)tokenizer.nextElement();
				}
				break;
			case SpliceToken:
				enterOuterAlt(_localctx, 3);
				{
				setState(77); ((SubtaskParseContext)_localctx).SpliceToken = match(SpliceToken);
				String subtaskParseString = (((SubtaskParseContext)_localctx).SpliceToken!=null?((SubtaskParseContext)_localctx).SpliceToken.getText():null);
							  String omitString = "(subtasks";				 
					   	 	  int startIndex = subtaskParseString.indexOf(omitString);
							  subtaskParseString = subtaskParseString.substring(startIndex);
							  startIndex = omitString.length()+1;		 
							  int endIndex = subtaskParseString.indexOf(')');
							  subtaskParseString = subtaskParseString.substring(startIndex,endIndex);
							  StringTokenizer tokenizer = new StringTokenizer(subtaskParseString);
				    		  int size = tokenizer.countTokens();
				              subtaskArray = new String[size];
				    	      for(int i = 0;i < size;i++)
				        	  	 subtaskArray[i] = (String)tokenizer.nextElement();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class QafParseContext extends ParserRuleContext {
		public Token TaskToken;
		public Token TaskGroupToken;
		public TerminalNode TaskGroupToken() { return getToken(CtaemsParser.TaskGroupToken, 0); }
		public TerminalNode TaskToken() { return getToken(CtaemsParser.TaskToken, 0); }
		public QafParseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qafParse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CtaemsListener ) ((CtaemsListener)listener).enterQafParse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CtaemsListener ) ((CtaemsListener)listener).exitQafParse(this);
		}
	}

	public final QafParseContext qafParse() throws RecognitionException {
		QafParseContext _localctx = new QafParseContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_qafParse);
		try {
			setState(85);
			switch (_input.LA(1)) {
			case TaskToken:
				enterOuterAlt(_localctx, 1);
				{
				setState(81); ((QafParseContext)_localctx).TaskToken = match(TaskToken);
				qafParseString = (((QafParseContext)_localctx).TaskToken!=null?((QafParseContext)_localctx).TaskToken.getText():null);
						  String omitString = "(qaf";				 
					   	  int startIndex = qafParseString.indexOf(omitString);
						  qafParseString = qafParseString.substring(startIndex);
						  startIndex = omitString.length()+1;		 
						  int endIndex = qafParseString.indexOf(')');
						  qafParseString = qafParseString.substring(startIndex,endIndex);
				}
				break;
			case TaskGroupToken:
				enterOuterAlt(_localctx, 2);
				{
				setState(83); ((QafParseContext)_localctx).TaskGroupToken = match(TaskGroupToken);
				qafParseString = (((QafParseContext)_localctx).TaskGroupToken!=null?((QafParseContext)_localctx).TaskGroupToken.getText():null);
						  String omitString = "(qaf";				 
					   	  int startIndex = qafParseString.indexOf(omitString);
						  qafParseString = qafParseString.substring(startIndex);
						  startIndex = omitString.length()+1;		 
						  int endIndex = qafParseString.indexOf(')');
						  qafParseString = qafParseString.substring(startIndex,endIndex);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FromParseContext extends ParserRuleContext {
		public Token FacilitatesToken;
		public Token HindersToken;
		public Token EnablesToken;
		public Token DisablesToken;
		public TerminalNode FacilitatesToken() { return getToken(CtaemsParser.FacilitatesToken, 0); }
		public TerminalNode DisablesToken() { return getToken(CtaemsParser.DisablesToken, 0); }
		public TerminalNode HindersToken() { return getToken(CtaemsParser.HindersToken, 0); }
		public TerminalNode EnablesToken() { return getToken(CtaemsParser.EnablesToken, 0); }
		public FromParseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fromParse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CtaemsListener ) ((CtaemsListener)listener).enterFromParse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CtaemsListener ) ((CtaemsListener)listener).exitFromParse(this);
		}
	}

	public final FromParseContext fromParse() throws RecognitionException {
		FromParseContext _localctx = new FromParseContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_fromParse);
		try {
			setState(95);
			switch (_input.LA(1)) {
			case FacilitatesToken:
				enterOuterAlt(_localctx, 1);
				{
				setState(87); ((FromParseContext)_localctx).FacilitatesToken = match(FacilitatesToken);
				fromParseString = (((FromParseContext)_localctx).FacilitatesToken!=null?((FromParseContext)_localctx).FacilitatesToken.getText():null);
						  		 String omitString = "(from";
								 int startIndex = fromParseString.indexOf(omitString);
								 fromParseString = fromParseString.substring(startIndex);
								 startIndex = omitString.length()+1;		 
								 int endIndex = fromParseString.indexOf(')');
								 fromParseString = fromParseString.substring(startIndex,endIndex);
				}
				break;
			case HindersToken:
				enterOuterAlt(_localctx, 2);
				{
				setState(89); ((FromParseContext)_localctx).HindersToken = match(HindersToken);
				fromParseString = (((FromParseContext)_localctx).HindersToken!=null?((FromParseContext)_localctx).HindersToken.getText():null);
						  		 String omitString = "(from";
								 int startIndex = fromParseString.indexOf(omitString);
								 fromParseString = fromParseString.substring(startIndex);
								 startIndex = omitString.length()+1;		 
								 int endIndex = fromParseString.indexOf(')');
								 fromParseString = fromParseString.substring(startIndex,endIndex);
				}
				break;
			case EnablesToken:
				enterOuterAlt(_localctx, 3);
				{
				setState(91); ((FromParseContext)_localctx).EnablesToken = match(EnablesToken);
				fromParseString = (((FromParseContext)_localctx).EnablesToken!=null?((FromParseContext)_localctx).EnablesToken.getText():null);
						  		 String omitString = "(from";
								 int startIndex = fromParseString.indexOf(omitString);
								 fromParseString = fromParseString.substring(startIndex);
								 startIndex = omitString.length()+1;		 
								 int endIndex = fromParseString.indexOf(')');
								 fromParseString = fromParseString.substring(startIndex,endIndex);
				}
				break;
			case DisablesToken:
				enterOuterAlt(_localctx, 4);
				{
				setState(93); ((FromParseContext)_localctx).DisablesToken = match(DisablesToken);
				fromParseString = (((FromParseContext)_localctx).DisablesToken!=null?((FromParseContext)_localctx).DisablesToken.getText():null);
						  		 String omitString = "(from";
								 int startIndex = fromParseString.indexOf(omitString);
								 fromParseString = fromParseString.substring(startIndex);
								 startIndex = omitString.length()+1;		 
								 int endIndex = fromParseString.indexOf(')');
								 fromParseString = fromParseString.substring(startIndex,endIndex);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ToParseContext extends ParserRuleContext {
		public Token FacilitatesToken;
		public Token HindersToken;
		public Token EnablesToken;
		public Token DisablesToken;
		public TerminalNode FacilitatesToken() { return getToken(CtaemsParser.FacilitatesToken, 0); }
		public TerminalNode DisablesToken() { return getToken(CtaemsParser.DisablesToken, 0); }
		public TerminalNode HindersToken() { return getToken(CtaemsParser.HindersToken, 0); }
		public TerminalNode EnablesToken() { return getToken(CtaemsParser.EnablesToken, 0); }
		public ToParseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_toParse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CtaemsListener ) ((CtaemsListener)listener).enterToParse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CtaemsListener ) ((CtaemsListener)listener).exitToParse(this);
		}
	}

	public final ToParseContext toParse() throws RecognitionException {
		ToParseContext _localctx = new ToParseContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_toParse);
		try {
			setState(105);
			switch (_input.LA(1)) {
			case FacilitatesToken:
				enterOuterAlt(_localctx, 1);
				{
				setState(97); ((ToParseContext)_localctx).FacilitatesToken = match(FacilitatesToken);
				toParseString = (((ToParseContext)_localctx).FacilitatesToken!=null?((ToParseContext)_localctx).FacilitatesToken.getText():null);
						  		 String omitString = "(to";
								 int startIndex = toParseString.indexOf(omitString);
								 toParseString = toParseString.substring(startIndex);
								 startIndex = omitString.length()+1;		 
								 int endIndex = toParseString.indexOf(')');
								 toParseString = toParseString.substring(startIndex,endIndex);
				}
				break;
			case HindersToken:
				enterOuterAlt(_localctx, 2);
				{
				setState(99); ((ToParseContext)_localctx).HindersToken = match(HindersToken);
				toParseString = (((ToParseContext)_localctx).HindersToken!=null?((ToParseContext)_localctx).HindersToken.getText():null);
						  		 String omitString = "(to";
								 int startIndex = toParseString.indexOf(omitString);
								 toParseString = toParseString.substring(startIndex);
								 startIndex = omitString.length()+1;		 
								 int endIndex = toParseString.indexOf(')');
								 toParseString = toParseString.substring(startIndex,endIndex);
				}
				break;
			case EnablesToken:
				enterOuterAlt(_localctx, 3);
				{
				setState(101); ((ToParseContext)_localctx).EnablesToken = match(EnablesToken);
				toParseString = (((ToParseContext)_localctx).EnablesToken!=null?((ToParseContext)_localctx).EnablesToken.getText():null);
						  		 String omitString = "(to";
								 int startIndex = toParseString.indexOf(omitString);
								 toParseString = toParseString.substring(startIndex);
								 startIndex = omitString.length()+1;		 
								 int endIndex = toParseString.indexOf(')');
								 toParseString = toParseString.substring(startIndex,endIndex);
				}
				break;
			case DisablesToken:
				enterOuterAlt(_localctx, 4);
				{
				setState(103); ((ToParseContext)_localctx).DisablesToken = match(DisablesToken);
				toParseString = (((ToParseContext)_localctx).DisablesToken!=null?((ToParseContext)_localctx).DisablesToken.getText():null);
						  		 String omitString = "(to";
								 int startIndex = toParseString.indexOf(omitString);
								 toParseString = toParseString.substring(startIndex);
								 startIndex = omitString.length()+1;		 
								 int endIndex = toParseString.indexOf(')');
								 toParseString = toParseString.substring(startIndex,endIndex);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class QualityPowerParseContext extends ParserRuleContext {
		public Token FacilitatesToken;
		public Token HindersToken;
		public TerminalNode FacilitatesToken() { return getToken(CtaemsParser.FacilitatesToken, 0); }
		public TerminalNode HindersToken() { return getToken(CtaemsParser.HindersToken, 0); }
		public QualityPowerParseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qualityPowerParse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CtaemsListener ) ((CtaemsListener)listener).enterQualityPowerParse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CtaemsListener ) ((CtaemsListener)listener).exitQualityPowerParse(this);
		}
	}

	public final QualityPowerParseContext qualityPowerParse() throws RecognitionException {
		QualityPowerParseContext _localctx = new QualityPowerParseContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_qualityPowerParse);
		try {
			setState(111);
			switch (_input.LA(1)) {
			case FacilitatesToken:
				enterOuterAlt(_localctx, 1);
				{
				setState(107); ((QualityPowerParseContext)_localctx).FacilitatesToken = match(FacilitatesToken);
				String qpParseString = (((QualityPowerParseContext)_localctx).FacilitatesToken!=null?((QualityPowerParseContext)_localctx).FacilitatesToken.getText():null);
						  		 String omitString = "(quality_power";
								 int startIndex = qpParseString.indexOf(omitString);
								 qpParseString = qpParseString.substring(startIndex);
								 startIndex = omitString.length()+1;		 
								 int endIndex = qpParseString.indexOf(')');
								 qpParseString = qpParseString.substring(startIndex,endIndex);
								 StringTokenizer tokenizer = new StringTokenizer(qpParseString);
				    			 int size = tokenizer.countTokens();
				                 qualityPowerArray = new double[size];
				    			 for(int i = 0;i < size;i++)
				        		 	qualityPowerArray[i] = Double.parseDouble((String)tokenizer.nextElement());
				}
				break;
			case HindersToken:
				enterOuterAlt(_localctx, 2);
				{
				setState(109); ((QualityPowerParseContext)_localctx).HindersToken = match(HindersToken);
				String qpParseString = (((QualityPowerParseContext)_localctx).HindersToken!=null?((QualityPowerParseContext)_localctx).HindersToken.getText():null);
						  		 String omitString = "(quality_power";
								 int startIndex = qpParseString.indexOf(omitString);
								 qpParseString = qpParseString.substring(startIndex);
								 startIndex = omitString.length()+1;		 
								 int endIndex = qpParseString.indexOf(')');
								 qpParseString = qpParseString.substring(startIndex,endIndex);
								 StringTokenizer tokenizer = new StringTokenizer(qpParseString);
				    			 int size = tokenizer.countTokens();
				                 qualityPowerArray = new double[size];
				    			 for(int i = 0;i < size;i++)
				        		 	qualityPowerArray[i] = Double.parseDouble((String)tokenizer.nextElement());
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DurationPowerParseContext extends ParserRuleContext {
		public Token FacilitatesToken;
		public Token HindersToken;
		public TerminalNode FacilitatesToken() { return getToken(CtaemsParser.FacilitatesToken, 0); }
		public TerminalNode HindersToken() { return getToken(CtaemsParser.HindersToken, 0); }
		public DurationPowerParseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_durationPowerParse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CtaemsListener ) ((CtaemsListener)listener).enterDurationPowerParse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CtaemsListener ) ((CtaemsListener)listener).exitDurationPowerParse(this);
		}
	}

	public final DurationPowerParseContext durationPowerParse() throws RecognitionException {
		DurationPowerParseContext _localctx = new DurationPowerParseContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_durationPowerParse);
		try {
			setState(117);
			switch (_input.LA(1)) {
			case FacilitatesToken:
				enterOuterAlt(_localctx, 1);
				{
				setState(113); ((DurationPowerParseContext)_localctx).FacilitatesToken = match(FacilitatesToken);
				String dpParseString = (((DurationPowerParseContext)_localctx).FacilitatesToken!=null?((DurationPowerParseContext)_localctx).FacilitatesToken.getText():null);
						  		 String omitString = "(duration_power";
								 int startIndex = dpParseString.indexOf(omitString);
								 dpParseString = dpParseString.substring(startIndex);
								 startIndex = omitString.length()+1;		 
								 int endIndex = dpParseString.indexOf(')');
								 dpParseString = dpParseString.substring(startIndex,endIndex);
								 StringTokenizer tokenizer = new StringTokenizer(dpParseString);
				    			 int size = tokenizer.countTokens();
				                 durationPowerArray = new double[size];
				    			 for(int i = 0;i < size;i++)
				        		 	durationPowerArray[i] = Double.parseDouble((String)tokenizer.nextElement());
				}
				break;
			case HindersToken:
				enterOuterAlt(_localctx, 2);
				{
				setState(115); ((DurationPowerParseContext)_localctx).HindersToken = match(HindersToken);
				String dpParseString = (((DurationPowerParseContext)_localctx).HindersToken!=null?((DurationPowerParseContext)_localctx).HindersToken.getText():null);
						  		 String omitString = "(duration_power";
								 int startIndex = dpParseString.indexOf(omitString);
								 dpParseString = dpParseString.substring(startIndex);
								 startIndex = omitString.length()+1;		 
								 int endIndex = dpParseString.indexOf(')');
								 dpParseString = dpParseString.substring(startIndex,endIndex);
								 StringTokenizer tokenizer = new StringTokenizer(dpParseString);
				    			 int size = tokenizer.countTokens();
				                 durationPowerArray = new double[size];
				    			 for(int i = 0;i < size;i++)
				        		 	durationPowerArray[i] = Double.parseDouble((String)tokenizer.nextElement());
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SpliceParseContext extends ParserRuleContext {
		public Token SpliceToken;
		public TerminalNode SpliceToken() { return getToken(CtaemsParser.SpliceToken, 0); }
		public SpliceParseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_spliceParse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CtaemsListener ) ((CtaemsListener)listener).enterSpliceParse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CtaemsListener ) ((CtaemsListener)listener).exitSpliceParse(this);
		}
	}

	public final SpliceParseContext spliceParse() throws RecognitionException {
		SpliceParseContext _localctx = new SpliceParseContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_spliceParse);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119); ((SpliceParseContext)_localctx).SpliceToken = match(SpliceToken);
			String spliceParseString = (((SpliceParseContext)_localctx).SpliceToken!=null?((SpliceParseContext)_localctx).SpliceToken.getText():null);
					  		String omitString = "(splice";
							int startIndex = spliceParseString.indexOf('(',3);	
							int endIndex = spliceParseString.indexOf(')');			
							spliceString = spliceParseString.substring(startIndex+1,endIndex);

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TimeParseContext extends ParserRuleContext {
		public Token TimeToken;
		public TerminalNode TimeToken() { return getToken(CtaemsParser.TimeToken, 0); }
		public TimeParseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_timeParse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CtaemsListener ) ((CtaemsListener)listener).enterTimeParse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CtaemsListener ) ((CtaemsListener)listener).exitTimeParse(this);
		}
	}

	public final TimeParseContext timeParse() throws RecognitionException {
		TimeParseContext _localctx = new TimeParseContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_timeParse);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122); ((TimeParseContext)_localctx).TimeToken = match(TimeToken);
			String timeParseString = (((TimeParseContext)_localctx).TimeToken!=null?((TimeParseContext)_localctx).TimeToken.getText():null);
						String omitString = "(time";
						int startIndex = timeParseString.indexOf(omitString);
						timeParseString = timeParseString.substring(startIndex);
				    	startIndex = omitString.length()+1;		 
						int endIndex = timeParseString.indexOf(')');
						timeParseString = timeParseString.substring(startIndex,endIndex);
						timeParseInt = Integer.parseInt(timeParseString);

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParseContext extends ParserRuleContext {
		public String token;
		public TerminalNode TaskGroupToken() { return getToken(CtaemsParser.TaskGroupToken, 0); }
		public TerminalNode TaskToken() { return getToken(CtaemsParser.TaskToken, 0); }
		public TerminalNode FacilitatesToken() { return getToken(CtaemsParser.FacilitatesToken, 0); }
		public TerminalNode HindersToken() { return getToken(CtaemsParser.HindersToken, 0); }
		public TerminalNode SpliceToken() { return getToken(CtaemsParser.SpliceToken, 0); }
		public TerminalNode EnablesToken() { return getToken(CtaemsParser.EnablesToken, 0); }
		public TerminalNode Spaces() { return getToken(CtaemsParser.Spaces, 0); }
		public TerminalNode MethodToken() { return getToken(CtaemsParser.MethodToken, 0); }
		public TerminalNode VersionToken() { return getToken(CtaemsParser.VersionToken, 0); }
		public TerminalNode AgentToken() { return getToken(CtaemsParser.AgentToken, 0); }
		public TerminalNode EOF() { return getToken(CtaemsParser.EOF, 0); }
		public TerminalNode TimeToken() { return getToken(CtaemsParser.TimeToken, 0); }
		public TerminalNode DisablesToken() { return getToken(CtaemsParser.DisablesToken, 0); }
		public ParseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CtaemsListener ) ((CtaemsListener)listener).enterParse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CtaemsListener ) ((CtaemsListener)listener).exitParse(this);
		}
	}

	public final ParseContext parse() throws RecognitionException {
		ParseContext _localctx = new ParseContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_parse);
		try {
			setState(151);
			switch (_input.LA(1)) {
			case VersionToken:
				enterOuterAlt(_localctx, 1);
				{
				setState(125); match(VersionToken);

				}
				break;
			case AgentToken:
				enterOuterAlt(_localctx, 2);
				{
				setState(127); match(AgentToken);

				}
				break;
			case TaskToken:
				enterOuterAlt(_localctx, 3);
				{
				setState(129); match(TaskToken);

				}
				break;
			case TaskGroupToken:
				enterOuterAlt(_localctx, 4);
				{
				setState(131); match(TaskGroupToken);

				}
				break;
			case MethodToken:
				enterOuterAlt(_localctx, 5);
				{
				setState(133); match(MethodToken);

				}
				break;
			case EnablesToken:
				enterOuterAlt(_localctx, 6);
				{
				setState(135); match(EnablesToken);

				}
				break;
			case FacilitatesToken:
				enterOuterAlt(_localctx, 7);
				{
				setState(137); match(FacilitatesToken);

				}
				break;
			case HindersToken:
				enterOuterAlt(_localctx, 8);
				{
				setState(139); match(HindersToken);

				}
				break;
			case DisablesToken:
				enterOuterAlt(_localctx, 9);
				{
				setState(141); match(DisablesToken);

				}
				break;
			case Spaces:
				enterOuterAlt(_localctx, 10);
				{
				setState(143); match(Spaces);

				}
				break;
			case SpliceToken:
				enterOuterAlt(_localctx, 11);
				{
				setState(145); match(SpliceToken);

				}
				break;
			case TimeToken:
				enterOuterAlt(_localctx, 12);
				{
				setState(147); match(TimeToken);

				}
				break;
			case EOF:
				enterOuterAlt(_localctx, 13);
				{
				setState(149); match(EOF);
				token = "done";
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\16\u009c\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2\65"+
		"\n\2\3\3\3\3\3\3\3\4\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3"+
		"\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\5\nR\n\n\3\13\3\13\3\13\3\13"+
		"\5\13X\n\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\fb\n\f\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\5\rl\n\r\3\16\3\16\3\16\3\16\5\16r\n\16\3\17\3\17\3\17"+
		"\3\17\5\17x\n\17\3\20\3\20\3\20\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\5\22\u009a\n\22\3\22\2\2\23\2\4\6"+
		"\b\n\f\16\20\22\24\26\30\32\34\36 \"\2\2\u00a8\2\64\3\2\2\2\4\66\3\2\2"+
		"\2\69\3\2\2\2\b<\3\2\2\2\n?\3\2\2\2\fB\3\2\2\2\16E\3\2\2\2\20H\3\2\2\2"+
		"\22Q\3\2\2\2\24W\3\2\2\2\26a\3\2\2\2\30k\3\2\2\2\32q\3\2\2\2\34w\3\2\2"+
		"\2\36y\3\2\2\2 |\3\2\2\2\"\u0099\3\2\2\2$%\7\3\2\2%\65\b\2\1\2&\'\7\b"+
		"\2\2\'\65\b\2\1\2()\7\n\2\2)\65\b\2\1\2*+\7\t\2\2+\65\b\2\1\2,-\7\13\2"+
		"\2-\65\b\2\1\2./\7\6\2\2/\65\b\2\1\2\60\61\7\5\2\2\61\65\b\2\1\2\62\63"+
		"\7\7\2\2\63\65\b\2\1\2\64$\3\2\2\2\64&\3\2\2\2\64(\3\2\2\2\64*\3\2\2\2"+
		"\64,\3\2\2\2\64.\3\2\2\2\64\60\3\2\2\2\64\62\3\2\2\2\65\3\3\2\2\2\66\67"+
		"\7\7\2\2\678\b\3\1\28\5\3\2\2\29:\7\7\2\2:;\b\4\1\2;\7\3\2\2\2<=\7\7\2"+
		"\2=>\b\5\1\2>\t\3\2\2\2?@\7\7\2\2@A\b\6\1\2A\13\3\2\2\2BC\7\4\2\2CD\b"+
		"\7\1\2D\r\3\2\2\2EF\7\6\2\2FG\b\b\1\2G\17\3\2\2\2HI\7\6\2\2IJ\b\t\1\2"+
		"J\21\3\2\2\2KL\7\6\2\2LR\b\n\1\2MN\7\5\2\2NR\b\n\1\2OP\7\r\2\2PR\b\n\1"+
		"\2QK\3\2\2\2QM\3\2\2\2QO\3\2\2\2R\23\3\2\2\2ST\7\6\2\2TX\b\13\1\2UV\7"+
		"\5\2\2VX\b\13\1\2WS\3\2\2\2WU\3\2\2\2X\25\3\2\2\2YZ\7\t\2\2Zb\b\f\1\2"+
		"[\\\7\13\2\2\\b\b\f\1\2]^\7\b\2\2^b\b\f\1\2_`\7\n\2\2`b\b\f\1\2aY\3\2"+
		"\2\2a[\3\2\2\2a]\3\2\2\2a_\3\2\2\2b\27\3\2\2\2cd\7\t\2\2dl\b\r\1\2ef\7"+
		"\13\2\2fl\b\r\1\2gh\7\b\2\2hl\b\r\1\2ij\7\n\2\2jl\b\r\1\2kc\3\2\2\2ke"+
		"\3\2\2\2kg\3\2\2\2ki\3\2\2\2l\31\3\2\2\2mn\7\t\2\2nr\b\16\1\2op\7\13\2"+
		"\2pr\b\16\1\2qm\3\2\2\2qo\3\2\2\2r\33\3\2\2\2st\7\t\2\2tx\b\17\1\2uv\7"+
		"\13\2\2vx\b\17\1\2ws\3\2\2\2wu\3\2\2\2x\35\3\2\2\2yz\7\r\2\2z{\b\20\1"+
		"\2{\37\3\2\2\2|}\7\16\2\2}~\b\21\1\2~!\3\2\2\2\177\u0080\7\4\2\2\u0080"+
		"\u009a\b\22\1\2\u0081\u0082\7\3\2\2\u0082\u009a\b\22\1\2\u0083\u0084\7"+
		"\6\2\2\u0084\u009a\b\22\1\2\u0085\u0086\7\5\2\2\u0086\u009a\b\22\1\2\u0087"+
		"\u0088\7\7\2\2\u0088\u009a\b\22\1\2\u0089\u008a\7\b\2\2\u008a\u009a\b"+
		"\22\1\2\u008b\u008c\7\t\2\2\u008c\u009a\b\22\1\2\u008d\u008e\7\13\2\2"+
		"\u008e\u009a\b\22\1\2\u008f\u0090\7\n\2\2\u0090\u009a\b\22\1\2\u0091\u0092"+
		"\7\f\2\2\u0092\u009a\b\22\1\2\u0093\u0094\7\r\2\2\u0094\u009a\b\22\1\2"+
		"\u0095\u0096\7\16\2\2\u0096\u009a\b\22\1\2\u0097\u0098\7\2\2\3\u0098\u009a"+
		"\b\22\1\2\u0099\177\3\2\2\2\u0099\u0081\3\2\2\2\u0099\u0083\3\2\2\2\u0099"+
		"\u0085\3\2\2\2\u0099\u0087\3\2\2\2\u0099\u0089\3\2\2\2\u0099\u008b\3\2"+
		"\2\2\u0099\u008d\3\2\2\2\u0099\u008f\3\2\2\2\u0099\u0091\3\2\2\2\u0099"+
		"\u0093\3\2\2\2\u0099\u0095\3\2\2\2\u0099\u0097\3\2\2\2\u009a#\3\2\2\2"+
		"\n\64QWakqw\u0099";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}