export default class MappingType {
  // 0,1,3,8 常用
  // 2,5,6,7 忽略
    /**
     * 任务类型-kettle，设计器里面添加的kettle转换作业。
     */
    public static readonly JOB_TYPE_KETTLE = '0';

    /**
     * 任务类型-yoyorepl
     */
    public static readonly JOB_TYPE_YOYOREPL = '1';
    /**
     * 任务类型-载数/卸数工具
     */
    public static readonly JOB_TYPE_EXPORT_IMPORT = '3';

    /**
     * 任务类型-oss文件传输s
     */
    public static readonly JOB_TYPE_OSS = '8';

  /* /!**
     * 任务类型-工作流(workflow)
     *!/
    public static readonly JOB_TYPE_WORKFLOW = '2';

    /!**
     * 地震台网中新项目使用任务
     *!/
    public static readonly JOB_TYPE_DRP = '5';

    /!**
     * 任务类型-导入的工作流 - kettle-job
     *!/
    public static readonly JOB_TYPE_KETTLE_JOB = '6';

    /!**
     * 任务类型-导入的转换 kettle-trans
     *!/
    public static readonly JOB_TYPE_KETTLE_TRANS = '7';*/
}
