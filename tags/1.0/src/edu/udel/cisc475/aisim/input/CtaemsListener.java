// Generated from Ctaems.g4 by ANTLR 4.2.2
package edu.udel.cisc475.aisim.input;import java.util.StringTokenizer;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CtaemsParser}.
 */
public interface CtaemsListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CtaemsParser#qualityPowerParse}.
	 * @param ctx the parse tree
	 */
	void enterQualityPowerParse(@NotNull CtaemsParser.QualityPowerParseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CtaemsParser#qualityPowerParse}.
	 * @param ctx the parse tree
	 */
	void exitQualityPowerParse(@NotNull CtaemsParser.QualityPowerParseContext ctx);

	/**
	 * Enter a parse tree produced by {@link CtaemsParser#densityParse}.
	 * @param ctx the parse tree
	 */
	void enterDensityParse(@NotNull CtaemsParser.DensityParseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CtaemsParser#densityParse}.
	 * @param ctx the parse tree
	 */
	void exitDensityParse(@NotNull CtaemsParser.DensityParseContext ctx);

	/**
	 * Enter a parse tree produced by {@link CtaemsParser#earliestStartTimeParse}.
	 * @param ctx the parse tree
	 */
	void enterEarliestStartTimeParse(@NotNull CtaemsParser.EarliestStartTimeParseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CtaemsParser#earliestStartTimeParse}.
	 * @param ctx the parse tree
	 */
	void exitEarliestStartTimeParse(@NotNull CtaemsParser.EarliestStartTimeParseContext ctx);

	/**
	 * Enter a parse tree produced by {@link CtaemsParser#agentParse}.
	 * @param ctx the parse tree
	 */
	void enterAgentParse(@NotNull CtaemsParser.AgentParseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CtaemsParser#agentParse}.
	 * @param ctx the parse tree
	 */
	void exitAgentParse(@NotNull CtaemsParser.AgentParseContext ctx);

	/**
	 * Enter a parse tree produced by {@link CtaemsParser#deadlineParse}.
	 * @param ctx the parse tree
	 */
	void enterDeadlineParse(@NotNull CtaemsParser.DeadlineParseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CtaemsParser#deadlineParse}.
	 * @param ctx the parse tree
	 */
	void exitDeadlineParse(@NotNull CtaemsParser.DeadlineParseContext ctx);

	/**
	 * Enter a parse tree produced by {@link CtaemsParser#toParse}.
	 * @param ctx the parse tree
	 */
	void enterToParse(@NotNull CtaemsParser.ToParseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CtaemsParser#toParse}.
	 * @param ctx the parse tree
	 */
	void exitToParse(@NotNull CtaemsParser.ToParseContext ctx);

	/**
	 * Enter a parse tree produced by {@link CtaemsParser#parse}.
	 * @param ctx the parse tree
	 */
	void enterParse(@NotNull CtaemsParser.ParseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CtaemsParser#parse}.
	 * @param ctx the parse tree
	 */
	void exitParse(@NotNull CtaemsParser.ParseContext ctx);

	/**
	 * Enter a parse tree produced by {@link CtaemsParser#labelParse}.
	 * @param ctx the parse tree
	 */
	void enterLabelParse(@NotNull CtaemsParser.LabelParseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CtaemsParser#labelParse}.
	 * @param ctx the parse tree
	 */
	void exitLabelParse(@NotNull CtaemsParser.LabelParseContext ctx);

	/**
	 * Enter a parse tree produced by {@link CtaemsParser#subtaskParse}.
	 * @param ctx the parse tree
	 */
	void enterSubtaskParse(@NotNull CtaemsParser.SubtaskParseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CtaemsParser#subtaskParse}.
	 * @param ctx the parse tree
	 */
	void exitSubtaskParse(@NotNull CtaemsParser.SubtaskParseContext ctx);

	/**
	 * Enter a parse tree produced by {@link CtaemsParser#versionParse}.
	 * @param ctx the parse tree
	 */
	void enterVersionParse(@NotNull CtaemsParser.VersionParseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CtaemsParser#versionParse}.
	 * @param ctx the parse tree
	 */
	void exitVersionParse(@NotNull CtaemsParser.VersionParseContext ctx);

	/**
	 * Enter a parse tree produced by {@link CtaemsParser#timeParse}.
	 * @param ctx the parse tree
	 */
	void enterTimeParse(@NotNull CtaemsParser.TimeParseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CtaemsParser#timeParse}.
	 * @param ctx the parse tree
	 */
	void exitTimeParse(@NotNull CtaemsParser.TimeParseContext ctx);

	/**
	 * Enter a parse tree produced by {@link CtaemsParser#spliceParse}.
	 * @param ctx the parse tree
	 */
	void enterSpliceParse(@NotNull CtaemsParser.SpliceParseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CtaemsParser#spliceParse}.
	 * @param ctx the parse tree
	 */
	void exitSpliceParse(@NotNull CtaemsParser.SpliceParseContext ctx);

	/**
	 * Enter a parse tree produced by {@link CtaemsParser#qualityDistributionParse}.
	 * @param ctx the parse tree
	 */
	void enterQualityDistributionParse(@NotNull CtaemsParser.QualityDistributionParseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CtaemsParser#qualityDistributionParse}.
	 * @param ctx the parse tree
	 */
	void exitQualityDistributionParse(@NotNull CtaemsParser.QualityDistributionParseContext ctx);

	/**
	 * Enter a parse tree produced by {@link CtaemsParser#qafParse}.
	 * @param ctx the parse tree
	 */
	void enterQafParse(@NotNull CtaemsParser.QafParseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CtaemsParser#qafParse}.
	 * @param ctx the parse tree
	 */
	void exitQafParse(@NotNull CtaemsParser.QafParseContext ctx);

	/**
	 * Enter a parse tree produced by {@link CtaemsParser#fromParse}.
	 * @param ctx the parse tree
	 */
	void enterFromParse(@NotNull CtaemsParser.FromParseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CtaemsParser#fromParse}.
	 * @param ctx the parse tree
	 */
	void exitFromParse(@NotNull CtaemsParser.FromParseContext ctx);

	/**
	 * Enter a parse tree produced by {@link CtaemsParser#durationDistributionParse}.
	 * @param ctx the parse tree
	 */
	void enterDurationDistributionParse(@NotNull CtaemsParser.DurationDistributionParseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CtaemsParser#durationDistributionParse}.
	 * @param ctx the parse tree
	 */
	void exitDurationDistributionParse(@NotNull CtaemsParser.DurationDistributionParseContext ctx);

	/**
	 * Enter a parse tree produced by {@link CtaemsParser#durationPowerParse}.
	 * @param ctx the parse tree
	 */
	void enterDurationPowerParse(@NotNull CtaemsParser.DurationPowerParseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CtaemsParser#durationPowerParse}.
	 * @param ctx the parse tree
	 */
	void exitDurationPowerParse(@NotNull CtaemsParser.DurationPowerParseContext ctx);
}