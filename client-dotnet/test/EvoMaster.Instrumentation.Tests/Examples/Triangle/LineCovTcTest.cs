using System.Collections.Generic;
using System.Linq;
using EvoMaster.Instrumentation.Examples.Triangle;
using EvoMaster.Instrumentation.StaticState;
using EvoMaster.Instrumentation_Shared;
using Xunit;
using Xunit.Abstractions;

namespace EvoMaster.Instrumentation.Tests.Examples.Triangle {
    public class LineCovTcTest : CovTcTestBase {
        private readonly ITestOutputHelper _testOutputHelper;

        public LineCovTcTest(ITestOutputHelper testOutputHelper) {
            _testOutputHelper = testOutputHelper;
        }

        [Fact]
        public void TestLineCoverage() {
            ITriangleClassification tc = new TriangleClassificationImpl();
        
            ExecutionTracer.Reset();
        
            Assert.Equal(0, ExecutionTracer.GetNumberOfObjectives());
        
            tc.Classify(-1, 0, 0);
        
            var a = ExecutionTracer.GetNumberOfObjectives();
            //at least one line should have been covered
            Assert.True(a > 0);
        }
        
        [Theory]
        [InlineData(-1, 0, 0, "Line_at_TriangleClassificationImpl_00007")]
        [InlineData(6, 6, 6, "Line_at_TriangleClassificationImpl_00011")]
        [InlineData(10, 6, 3, "Line_at_TriangleClassificationImpl_00019")]
        [InlineData(7, 6, 7, "Line_at_TriangleClassificationImpl_00023")]
        [InlineData(7, 6, 5, "Line_at_TriangleClassificationImpl_00026")]
        public void TestSpecificLineCoverage(int a, int b, int c, string returnLine) {
            _testOutputHelper.WriteLine("Test " + a);
            ITriangleClassification tc = new TriangleClassificationImpl();
        
            ExecutionTracer.Reset();
            ObjectiveRecorder.Reset(false);
        
            Assert.Equal(0, ExecutionTracer.GetNumberOfObjectives());
        
            tc.Classify(a, b, c);
        
            // Assert.Contains(returnLine, ObjectiveRecorder.AllTargets);
            // here is to check if the specified line is covered, ie, fitness value is 1.0
            Assert.Equal(1.0, ExecutionTracer.GetValue(returnLine));
        }
        
        [Theory]
        [InlineData(-1, 0, 0)]
        [InlineData(6, 6, 6)]
        [InlineData(10, 6, 3)]
        [InlineData(7, 6, 7)]
        [InlineData(7, 6, 5)]
        public void TestLastLineCoverage(int a, int b, int c) {
            ITriangleClassification tc = new TriangleClassificationImpl();
        
            ExecutionTracer.Reset();
            ObjectiveRecorder.Reset(false);
        
            Assert.Equal(0, ExecutionTracer.GetNumberOfObjectives());
        
            tc.Classify(a, b, c);
        
            //assert that the last line of the method is reached
            // Assert.Contains("Line_at_TriangleClassificationImpl_00027", ObjectiveRecorder.AllTargets);
        
            Assert.Equal(1.0, ExecutionTracer.GetValue("Line_at_TriangleClassificationImpl_00027"));
        }
        
        
        [Fact]
        public void TestAllTargetsGettingRegistered() {
            ExecutionTracer.Reset();
            ObjectiveRecorder.Reset(false);
        
            ITriangleClassification tc = new TriangleClassificationImpl();
        
            tc.Classify(3, 4, 5);
        
            var expectedLineNumbers = new List<int> {
                5, 6, 7, 10, 11, 14, 16, 18, 19, 22, 23, 26, 27
            };
        
            var expectedLines = new List<string>();
            expectedLineNumbers.ForEach(x =>
                expectedLines.Add(ObjectiveNaming.LineObjectiveName("TriangleClassificationImpl", x)));
        
        
            Assert.True(!expectedLines.Except(ObjectiveRecorder.AllTargets).Any());
            Assert.Contains(ObjectiveNaming.ClassObjectiveName("TriangleClassificationImpl"),
                ObjectiveRecorder.AllTargets);
        }
        
        [Fact]
        public void TestAllLinesGettingRegistered() {
            var expectedLineNumbers = new List<int> {
                5, 6, 7, 10, 11, 14, 16, 18, 19, 22, 23, 26, 27
            };
        
            var expectedLines = new List<string>();
            expectedLineNumbers.ForEach(x =>
                expectedLines.Add(ObjectiveNaming.LineObjectiveName("TriangleClassificationImpl", x)));
        
            var targets = GetRegisteredTargets();
        
            Assert.Equal(expectedLines, targets.Lines);
        }
        
        [Fact]
        public void TestAllClassesGettingRegistered() {
            var expectedClassNames = new List<string> {
                "TriangleClassificationImpl"
            };
        
            var expectedClasses = new List<string>();
        
            expectedClassNames.ForEach(x => expectedClasses.Add(ObjectiveNaming.ClassObjectiveName(x)));
        
            var targets = GetRegisteredTargets();
        
            Assert.Equal(expectedClasses, targets.Classes);
        }
    }
}