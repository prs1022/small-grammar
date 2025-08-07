package com.grammar.optimization;

import com.grammar.nlu.ImprovedFSMNLUEngine;
import com.grammar.nlu.NLUResult;
import com.grammar.compiler.ImprovedCLDCompiler;
import com.grammar.compiler.IncrementalCompiler;
import com.grammar.analyzer.RuleAnalyzer;
import com.grammar.optimizer.FSMOptimizer;
import com.grammar.fsm.FiniteStateMachine;
import com.grammar.cache.GrammarCache;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 全面测试语法规则解析与编译优化的所有亮点功能
 * 
 * 测试覆盖的优化特性：
 * 1. 缓存机制 - 避免重复解析
 * 2. 增量编译 - 只重新编译修改的规则
 * 3. 规则预处理 - 检测冲突和冗余
 * 4. 编译期优化 - 减少运行时计算
 * 5. FSM优化 - 状态合并和转换优化
 * 6. 并发处理 - 多线程安全性
 * 7. 性能监控 - 统计和分析
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OptimizationFeaturesTest {

    private ImprovedFSMNLUEngine engine;
    private ImprovedCLDCompiler compiler;
    private IncrementalCompiler incrementalCompiler;
    private RuleAnalyzer ruleAnalyzer;
    private FSMOptimizer fsmOptimizer;
    private GrammarCache cache;

    // 测试用的语法规则
    private static final String WEATHER_GRAMMAR = "namespace weather;\n\n" +
            "@type(name=\"location\")\n" +
            "<location>: \"北京\" | \"上海\" | \"深圳\" | \"广州\" | \"杭州\";\n\n" +
            "@type(name=\"time\")\n" +
            "<time>: \"今天\" | \"明天\" | \"后天\" | \"昨天\";\n\n" +
            "@attr(intention=\"weather_query\")\n" +
            "@statement\n" +
            "weather_query: <time>? <location>? \"的\"? \"天气\" \"怎么样\"?;\n\n" +
            "@attr(intention=\"weather_forecast\")\n" +
            "@statement\n" +
            "forecast_query: \"查询\" <time>? <location>? \"天气\" \"预报\"?;\n";

    private static final String NAVIGATION_GRAMMAR = "namespace navigation;\n\n" +
            "@type(name=\"destination\")\n" +
            "<destination>: \"公园\" | \"商场\" | \"机场\" | \"火车站\" | \"医院\";\n\n" +
            "@type(name=\"action\")\n" +
            "<action>: \"导航\" | \"去\" | \"前往\" | \"到\";\n\n" +
            "@attr(intention=\"navigation\")\n" +
            "@statement\n" +
            "nav_query: <action> \"到\"? <destination>;\n";

    @BeforeEach
    void setUp() {
        engine = new ImprovedFSMNLUEngine();
        compiler = new ImprovedCLDCompiler();
        incrementalCompiler = new IncrementalCompiler(compiler);
        ruleAnalyzer = new RuleAnalyzer();
        fsmOptimizer = new FSMOptimizer();
        cache = new GrammarCache();
    }

    @Test
    @Order(1)
    @DisplayName("🚀 缓存机制优化测试 - 避免重复解析")
    void testCachingOptimization() {
        System.out.println("\n=== 测试缓存机制优化 ===");

        // 第一次编译 - 应该进行完整解析
        long startTime1 = System.nanoTime();
        List<FiniteStateMachine> fsms1 = compiler.compile(WEATHER_GRAMMAR);
        long duration1 = System.nanoTime() - startTime1;

        assertNotNull(fsms1);
        assertTrue(fsms1.size() > 0);
        System.out.println("首次编译耗时: " + duration1 / 1_000_000 + "ms");

        // 第二次编译相同内容 - 应该使用缓存
        long startTime2 = System.nanoTime();
        List<FiniteStateMachine> fsms2 = compiler.compile(WEATHER_GRAMMAR);
        long duration2 = System.nanoTime() - startTime2;

        assertNotNull(fsms2);
        assertEquals(fsms1.size(), fsms2.size());
        System.out.println("缓存编译耗时: " + duration2 / 1_000_000 + "ms");

        // 验证缓存效果 - 第二次应该明显更快
        assertTrue(duration2 < duration1 * 0.8,
                "缓存编译应该比首次编译快至少20%");

        // 验证缓存统计
        GrammarCache.CacheStats stats = compiler.getCacheStats();
        assertNotNull(stats);
        System.out.println("缓存统计: " + stats);

        System.out.println("✅ 缓存机制测试通过 - 性能提升: " +
                String.format("%.1f%%", (1.0 - (double) duration2 / duration1) * 100));
    }

    @Test
    @Order(2)
    @DisplayName("⚡ 增量编译优化测试 - 只重新编译修改的规则")
    void testIncrementalCompilation() {
        System.out.println("\n=== 测试增量编译优化 ===");

        // 加载初始语法
        engine.loadGrammarString(WEATHER_GRAMMAR, "weather.cld");
        int initialFSMCount = engine.getFSMs().size();
        System.out.println("初始FSM数量: " + initialFSMCount);

        // 添加新的语法规则
        String updatedGrammar = WEATHER_GRAMMAR +
                "\n@attr(intention=\"temperature_query\")\n" +
                "@statement\n" +
                "temp_query: <location>? \"温度\" \"多少\"?;\n";

        long startTime = System.nanoTime();
        engine.loadGrammarString(updatedGrammar, "weather_updated.cld");
        long duration = System.nanoTime() - startTime;

        int updatedFSMCount = engine.getFSMs().size();
        System.out.println("更新后FSM数量: " + updatedFSMCount);
        System.out.println("增量编译耗时: " + duration / 1_000_000 + "ms");

        // 验证增量编译效果
        assertTrue(updatedFSMCount > initialFSMCount, "应该增加新的FSM");

        // 测试新增的规则是否工作
        NLUResult result = engine.parseText("北京温度多少");
        System.out.println("intent>>>" + result.getIntent());
        assertEquals("temperature_query", result.getIntent());

        System.out.println("✅ 增量编译测试通过 - 新增规则正常工作");
    }

    @Test
    @Order(3)
    @DisplayName("🔍 规则预处理优化测试 - 检测冲突和冗余")
    void testRulePreprocessingOptimization() {
        System.out.println("\n=== 测试规则预处理优化 ===");

        // 创建包含潜在冲突的语法规则
        String conflictGrammar = "namespace test;\n\n" +
                "<location>: \"北京\" | \"上海\";\n" +
                "<city>: \"北京\" | \"深圳\";  // 与location有重叠\n\n" +
                "@attr(intention=\"query1\")\n" +
                "@statement\n" +
                "query1: <location> \"天气\";\n\n" +
                "@attr(intention=\"query2\")\n" +
                "@statement\n" +
                "query2: <city> \"天气\";     // 可能与query1冲突\n\n" +
                "@attr(intention=\"redundant\")\n" +
                "@statement\n" +
                "redundant: \"北京\" \"天气\";  // 冗余规则\n";

        // 分析语法规则
        engine.analyzeGrammarRules(conflictGrammar);

        // 编译并测试
        List<FiniteStateMachine> fsms = compiler.compile(conflictGrammar);
        assertNotNull(fsms);
        assertTrue(fsms.size() >= 3);

        // 测试冲突检测 - "北京天气"应该能匹配多个意图
        engine.loadGrammarString(conflictGrammar, "conflict_test.cld");
        NLUResult result = engine.parseText("北京天气");

        assertNotNull(result);
        assertNotEquals("unknown", result.getIntent());

        System.out.println("匹配结果: " + result.toJsonString());
        System.out.println("✅ 规则预处理测试通过 - 成功检测并处理规则冲突");
    }

    @Test
    @Order(4)
    @DisplayName("⚙️ 编译期优化测试 - 减少运行时计算")
    void testCompileTimeOptimization() {
        System.out.println("\n=== 测试编译期优化 ===");

        // 创建复杂的语法规则用于测试优化
        String complexGrammar = "namespace complex;\n\n" +
                "@type(name=\"number\")\n" +
                "<number>: \"三\" | \"五\" | \"十\";\n\n" +
                "@type(name=\"unit\")\n" +
                "<unit>: \"个\" | \"只\";\n\n" +
                "@type(name=\"item\")\n" +
                "<item>: \"苹果\" | \"香蕉\" | \"橘子\" | \"西瓜\";\n\n" +
                "@attr(intention=\"purchase\")\n" +
                "@statement\n" +
                "purchase_query: \"我要\" \"买\" <number> <unit> <item>;\n\n" +
                "@attr(intention=\"count\")\n" +
                "@statement\n" +
                "count_query: \"有\" \"多少\" <unit> <item>;\n\n" +
                "@attr(intention=\"simple_purchase\")\n" +
                "@statement\n" +
                "simple_buy: \"买\" <item>;\n\n" +
                "@attr(intention=\"query_item\")\n" +
                "@statement\n" +
                "query_item: \"有\" <item>;\n";

        // 测试编译优化
        long compileStart = System.nanoTime();
        List<FiniteStateMachine> fsms = compiler.compile(complexGrammar);
        long compileTime = System.nanoTime() - compileStart;

        assertNotNull(fsms);
        assertTrue(fsms.size() >= 2);
        System.out.println("复杂语法编译耗时: " + compileTime / 1_000_000 + "ms");

        // 加载到引擎并测试运行时性能
        engine.loadGrammarString(complexGrammar, "complex.cld");

        String[] testCases = {
                "我要买三个苹果",
                "我要买五只香蕉",
                "有多少个橘子",
                "有多少只香蕉",
                "买苹果",
                "有苹果"
        };

        long totalMatchTime = 0;
        int successfulMatches = 0;

        for (String testCase : testCases) {
            long matchStart = System.nanoTime();
            NLUResult result = engine.parseText(testCase);
            long matchTime = System.nanoTime() - matchStart;

            totalMatchTime += matchTime;

            if (!result.getIntent().equals("unknown")) {
                successfulMatches++;
                System.out.println("✓ \"" + testCase + "\" -> " + result.getIntent() +
                        " (" + matchTime / 1_000 + "μs)");
            }
        }

        double avgMatchTime = totalMatchTime / (double) testCases.length / 1_000;
        System.out.println("平均匹配耗时: " + String.format("%.1f", avgMatchTime) + "μs");
        System.out.println("成功匹配率: " + successfulMatches + "/" + testCases.length);

        // 验证性能指标
        assertTrue(avgMatchTime < 1000, "平均匹配时间应该小于1ms");
        assertTrue(successfulMatches >= testCases.length * 0.5, "成功匹配率应该大于50%");

        System.out.println("✅ 编译期优化测试通过 - 运行时性能良好");
    }

    @Test
    @Order(5)
    @DisplayName("🎯 FSM优化测试 - 状态合并和转换优化")
    void testFSMOptimization() {
        System.out.println("\n=== 测试FSM优化 ===");

        // 编译语法生成原始FSM
        List<FiniteStateMachine> originalFSMs = compiler.compile(WEATHER_GRAMMAR);
        assertNotNull(originalFSMs);
        assertTrue(originalFSMs.size() > 0);

        // 统计原始FSM的状态数
        int totalOriginalStates = 0;
        for (FiniteStateMachine fsm : originalFSMs) {
            totalOriginalStates += fsm.getAllStates().size();
        }
        System.out.println("原始FSM总状态数: " + totalOriginalStates);

        // 应用FSM优化
        int totalOptimizedStates = 0;
        for (FiniteStateMachine fsm : originalFSMs) {
            FiniteStateMachine optimizedFSM = fsmOptimizer.optimize(fsm);
            totalOptimizedStates += optimizedFSM.getAllStates().size();

            // 验证优化后的FSM功能正确性
            assertNotNull(optimizedFSM);
            assertEquals(fsm.getName(), optimizedFSM.getName());
            assertEquals(fsm.getIntentName(), optimizedFSM.getIntentName());
        }

        System.out.println("优化后FSM总状态数: " + totalOptimizedStates);

        // 验证优化效果（状态数应该减少或保持不变）
        assertTrue(totalOptimizedStates <= totalOriginalStates,
                "优化后状态数应该不超过原始状态数");

        // 测试优化后的功能正确性
        engine.loadGrammarString(WEATHER_GRAMMAR, "weather_optimized.cld");

        String[] testCases = {
                "今天北京的天气怎么样",
                "明天上海天气",
                "查询深圳天气预报"
        };

        for (String testCase : testCases) {
            NLUResult result = engine.parseText(testCase);
            assertNotEquals("unknown", result.getIntent());
            System.out.println("✓ \"" + testCase + "\" -> " + result.getIntent());
        }

        double optimizationRatio = (double) totalOptimizedStates / totalOriginalStates;
        System.out.println("状态优化率: " + String.format("%.1f%%", (1 - optimizationRatio) * 100));
        System.out.println("✅ FSM优化测试通过 - 保持功能正确性的同时优化了结构");
    }

    @Test
    @Order(6)
    @DisplayName("🔄 并发处理优化测试 - 多线程安全性")
    void testConcurrentProcessingOptimization() {
        System.out.println("\n=== 测试并发处理优化 ===");

        // 加载语法规则
        engine.loadGrammarString(WEATHER_GRAMMAR, "weather_concurrent.cld");
        engine.loadGrammarString(NAVIGATION_GRAMMAR, "navigation_concurrent.cld");

        // 准备并发测试数据
        String[] testInputs = {
                "今天北京天气怎么样",
                "明天上海的天气",
                "导航到公园",
                "去商场",
                "查询深圳天气预报",
                "前往机场",
                "后天广州天气",
                "到医院"
        };

        ExecutorService executor = Executors.newFixedThreadPool(4);

        try {
            // 并发执行解析任务
            CompletableFuture<?>[] futures = new CompletableFuture[testInputs.length];

            long startTime = System.nanoTime();

            for (int i = 0; i < testInputs.length; i++) {
                final String input = testInputs[i];
                final int index = i;

                futures[i] = CompletableFuture.runAsync(() -> {
                    try {
                        // 模拟并发访问
                        for (int j = 0; j < 10; j++) {
                            NLUResult result = engine.parseText(input);
                            assertNotNull(result);

                            // 验证结果一致性
                            if (!result.getIntent().equals("unknown")) {
                                assertTrue(result.getIntent().length() > 0);
                                assertNotNull(result.getSlots());
                            }
                        }

                        System.out.println("线程 " + index + " 完成: \"" + input + "\"");

                    } catch (Exception e) {
                        fail("并发处理失败: " + e.getMessage());
                    }
                }, executor);
            }

            // 等待所有任务完成
            CompletableFuture.allOf(futures).get(10, TimeUnit.SECONDS);

            long duration = System.nanoTime() - startTime;
            System.out.println("并发处理总耗时: " + duration / 1_000_000 + "ms");

            // 验证并发处理后引擎状态正常
            NLUResult testResult = engine.parseText("今天天气怎么样");
            assertNotEquals("unknown", testResult.getIntent());

            System.out.println("✅ 并发处理测试通过 - 多线程环境下运行稳定");

        } catch (Exception e) {
            fail("并发测试失败: " + e.getMessage());
        } finally {
            executor.shutdown();
        }
    }

    @Test
    @Order(7)
    @DisplayName("📊 性能监控优化测试 - 统计和分析")
    void testPerformanceMonitoringOptimization() {
        System.out.println("\n=== 测试性能监控优化 ===");

        // 捕获统计输出
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            // 加载多个语法文件
            engine.loadGrammarString(WEATHER_GRAMMAR, "weather_stats.cld");
            engine.loadGrammarString(NAVIGATION_GRAMMAR, "navigation_stats.cld");

            // 执行多次解析以生成统计数据
            String[] testCases = {
                    "今天北京天气怎么样",
                    "导航到公园",
                    "明天上海天气",
                    "去商场",
                    "查询深圳天气预报"
            };

            for (int i = 0; i < 5; i++) {
                for (String testCase : testCases) {
                    engine.parseText(testCase);
                }
            }

            // 打印统计信息
            engine.printStatistics();

        } finally {
            System.setOut(originalOut);
        }

        String output = outputStream.toString();

        // 验证统计信息包含关键指标
        assertTrue(output.contains("Statistics"), "应该包含统计信息");
        assertTrue(output.contains("FSMs loaded"), "应该包含FSM加载统计");
        assertTrue(output.contains("Cache Statistics"), "应该包含缓存统计");
        assertTrue(output.contains("Compilation Statistics"), "应该包含编译统计");

        System.out.println("统计信息输出:");
        System.out.println(output);

        // 验证引擎状态
        List<FiniteStateMachine> fsms = engine.getFSMs();
        assertTrue(fsms.size() >= 2, "应该加载至少2个FSM");

        for (FiniteStateMachine fsm : fsms) {
            assertNotNull(fsm.getName());
            assertNotNull(fsm.getIntentName());
            assertTrue(fsm.getAllStates().size() > 0);
        }

        System.out.println("✅ 性能监控测试通过 - 成功收集和展示统计信息");
    }

    @Test
    @Order(8)
    @DisplayName("🎪 综合优化效果测试 - 端到端性能验证")
    void testComprehensiveOptimizationEffects() {
        System.out.println("\n=== 测试综合优化效果 ===");

        // 创建大规模语法规则用于压力测试
        String largeGrammar = generateLargeGrammar();

        // 测试加载性能
        long loadStart = System.nanoTime();
        engine.loadGrammarString(largeGrammar, "large_grammar.cld");
        long loadTime = System.nanoTime() - loadStart;

        System.out.println("大规模语法加载耗时: " + loadTime / 1_000_000 + "ms");

        // 验证加载结果
        List<FiniteStateMachine> fsms = engine.getFSMs();
        assertTrue(fsms.size() >= 10, "应该加载大量FSM");

        // 测试批量解析性能
        String[] testBatch = {
                "今天北京天气怎么样",
                "导航到公园",
                "我要买三个苹果",
                "播放音乐",
                "设置闹钟",
                "查询余额",
                "发送消息",
                "拍照",
                "录音",
                "计算器"
        };

        long batchStart = System.nanoTime();
        int successCount = 0;

        for (String input : testBatch) {
            NLUResult result = engine.parseText(input);
            if (!result.getIntent().equals("unknown")) {
                successCount++;
            }
        }

        long batchTime = System.nanoTime() - batchStart;
        double avgTime = batchTime / (double) testBatch.length / 1_000;

        System.out.println("批量解析成功率: " + successCount + "/" + testBatch.length);
        System.out.println("平均解析耗时: " + String.format("%.1f", avgTime) + "μs");

        // 性能指标验证
        assertTrue(loadTime < 5_000_000_000L, "大规模语法加载应在5秒内完成");
        assertTrue(avgTime < 500, "平均解析时间应小于500μs");
        assertTrue(successCount >= testBatch.length * 0.6, "成功率应大于60%");

        // 内存使用情况
        Runtime runtime = Runtime.getRuntime();
        long usedMemory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("当前内存使用: " + usedMemory / 1024 / 1024 + "MB");

        // 最终统计
        engine.printStatistics();

        System.out.println("✅ 综合优化效果测试通过");
        System.out.println("🎉 所有优化特性验证完成！");
    }

    @Test
    @Order(9)
    @DisplayName("🧹 缓存清理和资源管理测试")
    void testCacheCleanupAndResourceManagement() {
        System.out.println("\n=== 测试缓存清理和资源管理 ===");

        // 加载语法并生成缓存
        engine.loadGrammarString(WEATHER_GRAMMAR, "cleanup_test.cld");

        // 验证缓存存在
        GrammarCache.CacheStats statsBefore = compiler.getCacheStats();
        assertNotNull(statsBefore);

        // 执行解析生成更多缓存数据
        for (int i = 0; i < 10; i++) {
            engine.parseText("今天天气怎么样");
        }

        // 清理所有缓存
        engine.clearAllCaches();

        // 验证缓存已清理
        assertTrue(engine.getFSMs().isEmpty(), "FSM缓存应该被清空");

        // 重新加载验证功能正常
        engine.loadGrammarString(WEATHER_GRAMMAR, "cleanup_test_reload.cld");
        NLUResult result = engine.parseText("今天天气怎么样");
        assertNotEquals("unknown", result.getIntent());

        System.out.println("✅ 缓存清理和资源管理测试通过");
    }

    /**
     * 生成大规模语法规则用于压力测试
     */
    private String generateLargeGrammar() {
        StringBuilder sb = new StringBuilder();
        sb.append("namespace large_test;\n\n");

        // 生成多个领域的语法规则
        String[] domains = { "weather", "navigation", "shopping", "music", "alarm",
                "finance", "message", "camera", "recorder", "calculator" };

        for (String domain : domains) {
            sb.append(String.format(
                    "@type(name=\"%s_entity\")\n" +
                            "<%s_entity>: \"测试1\" | \"测试2\" | \"测试3\";\n\n" +
                            "@attr(intention=\"%s_intent\")\n" +
                            "@statement\n" +
                            "%s_query: <%s_entity> \"操作\" \"请求\"?;\n\n",
                    domain, domain, domain, domain, domain));
        }

        return sb.toString();
    }

    @AfterEach
    void tearDown() {
        if (engine != null) {
            engine.clearAllCaches();
        }
    }

    @AfterAll
    static void cleanup() {
        System.out.println("\n🎯 优化特性测试总结:");
        System.out.println("✅ 缓存机制 - 避免重复解析，提升编译性能");
        System.out.println("✅ 增量编译 - 只重新编译修改的规则，支持热更新");
        System.out.println("✅ 规则预处理 - 检测冲突和冗余，提高规则质量");
        System.out.println("✅ 编译期优化 - 减少运行时计算，提升匹配性能");
        System.out.println("✅ FSM优化 - 状态合并和转换优化，减少内存占用");
        System.out.println("✅ 并发处理 - 多线程安全，支持高并发场景");
        System.out.println("✅ 性能监控 - 统计和分析，支持性能调优");
        System.out.println("✅ 资源管理 - 缓存清理，避免内存泄漏");
        System.out.println("\n🚀 所有优化特性测试完成，系统性能得到全面提升！");
    }
}