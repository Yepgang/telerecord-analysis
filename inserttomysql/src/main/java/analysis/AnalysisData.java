package analysis;

import analysis.tool.AnalysisTextTool;
import org.apache.hadoop.util.ToolRunner;

public class AnalysisData {
    public static void main(String[] args) throws Exception {

        int result = ToolRunner.run( new AnalysisTextTool(), args );
//        int result = ToolRunner.run( new AnalysisBeanTool(), args );

    }
}

