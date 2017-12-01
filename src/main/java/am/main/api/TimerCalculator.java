//package am.main.api;
//
//import am.main.common.ConfigParam;
//import am.main.common.ConfigUtils;
//import am.main.core.config.AMConfigurationManager;
//import am.main.data.enums.AM_CC;
//import am.main.data.enums.Source;
//import am.main.session.AppSession;
//import am.shared.session.Phase;
//import net.objectlab.kit.datecalc.common.HolidayCalendar;
//import net.objectlab.kit.datecalc.jdk8.LocalDateCalculator;
//
//import javax.annotation.PostConstruct;
//import javax.inject.Inject;
//import java.io.*;
//import java.time.*;
//import java.time.format.DateTimeParseException;
//import java.util.*;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
///**
// * Created by ahmed.motair on 11/30/2017.
// */
//public class TimerCalculator {
//
//    @Inject private AMConfigurationManager amConfigManager;
//    @Inject private AppLogger logger;
//    private static Properties TIMER_CALENDER_CONFIG;
//    private static List<String> PUBLIC_HOLIDAYS;
//    private static final String CLASS = "TimerCalculator";
//    public static TimerCalculator instance;
//
//    private static LocalDateCalculator dateCalculator;
//    private int workingHours;
//    private LocalTime startTime;
//    private LocalTime endTime;
//    private HolidayCalendar<LocalDate> holidayCalendar;
//
//    private final String BEFORE_WH = "B";
//    private final String AFTER_WH = "A";
//    private final String WITHIN_WH = "I";
//    private final String IN_HOLIDAY = "H";
//
//    private static final String WORKING_HOUR = "WorkingHours";
//    private static final String START_TIME = "StartTime";
//    private static final String END_TIME = "EndTime";
//    private static final String HOLIDAYS = "Holidays";
//    private static final String WEEKEND = "Weekend";
//
//    private TimerCalculator() {
//
//    }
//
//    public static TimerCalculator getInstance() throws Exception{
//        if (instance == null){
//            synchronized(TimerCalculator.class){
//                if (instance == null){
//                    instance = new TimerCalculator();
//                    instance.load();
//                }
//            }
//        }
//        return instance;
//    }
//
//
//    @PostConstruct
//    private void load(){
//        String FN_NAME = "load";
//        AppSession session = new AppSession(Source.AM, Phase.AM_INITIALIZING, CLASS, FN_NAME);
//
//        String TIMER_CALC_CONFIG_FILE_NAME = amConfigManager.getConfigValue(session, AM_CC.TIMER_CALCULATOR_CONFIG);
//        ConfigParam.FILE.TIMER_CALCULATOR_CONFIG = ConfigParam.APP_CONFIG_PATH + TIMER_CALC_CONFIG_FILE_NAME;
//        TIMER_CALENDER_CONFIG = ConfigUtils.loadPropertySystemComponent(session, ConfigParam.FILE.TIMER_CALCULATOR_CONFIG, ConfigParam.COMPONENT.TIMER_CALCULATOR);
//
//        String PUBLIC_HOLIDAYS_FILE_NAME = amConfigManager.getConfigValue(session, AM_CC.PUBLIC_HOLIDAYS);
//        ConfigParam.FILE.PUBLIC_HOLIDAYS = ConfigParam.APP_CONFIG_PATH + TIMER_CALC_CONFIG_FILE_NAME;
//        PUBLIC_HOLIDAYS = ConfigUtils.loadTextSystemComponent(session, ConfigParam.FILE.PUBLIC_HOLIDAYS, ConfigParam.COMPONENT.PUBLIC_HOLIDAYS);
//
//        initializeTimerCalculator(session);
//        //TODO: Check if the File Not Found Log Message that it has to be with the name in the Property File
//    }
//
//    /**
//     * Initialize the Timer Calculator by setting the workingHours, working StartTime,
//     * working endTime specifying the working days and holidays
//     * These Calendar Data are fetched from the Calendar Config Property File
//     */
//    public void initializeTimerCalculator(AppSession session){
//        final String FN_NAME = "TimerCalculator";
//        logger.startDebug(session);
//
//        // Assigning the Working Hours
//        assignWorkingHours(session);
//
//        // Assigning the Holidays of the Year
//        assignCalendarHolidays(session);
//
//        // Assign the Weekend Days of the Week
//        assignWeekends(session);
//
//        logger.endDebug(session);
//    }
//
//    private void assignWorkingHours(AppSession session){
//        String FN_NAME = "assignWorkingHours";
//        logger.startDebug(session);
//
//        // Assigning the Working Hours
//        try {
//            String workingHoursProp = TIMER_CALENDER_CONFIG.getProperty(WORKING_HOUR);
//            this.workingHours = Integer.parseInt(workingHoursProp);
//
//            logger.infoLog(session, getClass(), FN_NAME, "Working Hours are assigned Successfully in the Timer Calculator");
//        } catch (NumberFormatException ex) {
//            logger.error(session, getClass(), FN_NAME, ex.getMessage());
//            throw new BusinessException(ex, NPCErrorHandler.getMsg(logger, session, NPCError.GS_ERROR_0009, WORKING_HOUR));
//        } catch (NullPointerException ex) {
//            logger.error(session, getClass(), FN_NAME, ex.getMessage());
//            throw new BusinessException(ex, NPCErrorHandler.getMsg(logger, session, NPCError.GS_ERROR_0010, WORKING_HOUR));
//        }
//
//        // Assigning the Start Working Time
//        try {
//            String[] startTimeProp = calendarConfig.getProperty(START_TIME).split(":");
//            this.startTime = LocalTime.of(Integer.parseInt(startTimeProp[0]), Integer.parseInt(startTimeProp[1]));
//
//            logger.infoLog(session, getClass(), FN_NAME, "Start Time of Working Hours are assigned Successfully in the Timer Calculator");
//        } catch (NumberFormatException ex) {
//            logger.error(session, getClass(), FN_NAME, ex.getMessage());
//            throw new BusinessException(ex, NPCErrorHandler.getMsg(logger, session, NPCError.GS_ERROR_0009, START_TIME));
//        } catch (NullPointerException ex) {
//            logger.error(session, getClass(), FN_NAME, ex.getMessage());
//            throw new BusinessException(ex, NPCErrorHandler.getMsg(logger, session, NPCError.GS_ERROR_0010, START_TIME));
//        }
//
//        // Assigning the End Working Time
//        try {
//            String[] endTimeProp = calendarConfig.getProperty(END_TIME).split(":");
//            this.endTime = LocalTime.of(Integer.parseInt(endTimeProp[0]), Integer.parseInt(endTimeProp[1]));
//
//            logger.infoLog(session, getClass(), FN_NAME, "End Time of Working Hours are assigned Successfully in the Timer Calculator");
//        } catch (NumberFormatException ex) {
//            logger.error(session, getClass(), FN_NAME, ex.getMessage());
//            throw new BusinessException(ex, NPCErrorHandler.getMsg(logger, session, NPCError.GS_ERROR_0009, END_TIME));
//        } catch (NullPointerException ex) {
//            logger.error(session, getClass(), FN_NAME, ex.getMessage());
//            throw new BusinessException(ex, NPCErrorHandler.getMsg(logger, session, NPCError.GS_ERROR_0010, END_TIME));
//        }
//
//        logger.endDebugLog(session, getClass(), FN_NAME);
//
//    }
//
//    private void assignWeekends(AppSession session) throws Exception {
//        String FN_NAME = "assignWeekends";
//        logger.startDebugLog(session, getClass(), FN_NAME);
//        try {
//            String[] weekendProp = calendarConfig.getProperty(WEEKEND).split(",");
//
//            Jdk8WorkingWeek workingWeek = new Jdk8WorkingWeek(WorkingWeek.DEFAULT);
//            workingWeek = workingWeek.withWorkingDayFromDateTimeConstant(true, DayOfWeek.SATURDAY);
//            workingWeek = workingWeek.withWorkingDayFromDateTimeConstant(true, DayOfWeek.SUNDAY);
//
//            for (String weekend : weekendProp) {
//                switch (weekend.trim().toLowerCase()) {
//                    case "monday":
//                        workingWeek = workingWeek.withWorkingDayFromDateTimeConstant(false, DayOfWeek.MONDAY);
//                        break;
//                    case "tuesday":
//                        workingWeek = workingWeek.withWorkingDayFromDateTimeConstant(false, DayOfWeek.TUESDAY);
//                        break;
//                    case "wednesday":
//                        workingWeek = workingWeek.withWorkingDayFromDateTimeConstant(false, DayOfWeek.WEDNESDAY);
//                        break;
//                    case "thursday":
//                        workingWeek = workingWeek.withWorkingDayFromDateTimeConstant(false, DayOfWeek.THURSDAY);
//                        break;
//                    case "friday":
//                        workingWeek = workingWeek.withWorkingDayFromDateTimeConstant(false, DayOfWeek.FRIDAY);
//                        break;
//                    case "saturday":
//                        workingWeek = workingWeek.withWorkingDayFromDateTimeConstant(false, DayOfWeek.SATURDAY);
//                        break;
//                    case "sunday":
//                        workingWeek = workingWeek.withWorkingDayFromDateTimeConstant(false, DayOfWeek.SUNDAY);
//                        break;
//                    default:
//                        throw new BusinessException(NPCErrorHandler.getMsg(logger, session, NPCError.GS_ERROR_0008));
//                }
//            }
//
//            dateCalculator.setWorkingWeek(workingWeek);
//            logger.infoLog(session, getClass(), FN_NAME, "Weekends are assigned Successfully in the Timer Calculator");
//
//            logger.endDebugLog(session, getClass(), FN_NAME);
//        } catch (DateTimeParseException ex) {
//            logger.error(session, getClass(), FN_NAME, ex.getMessage());
//            throw new BusinessException(ex, NPCErrorHandler.getMsg(logger, session, NPCError.GS_ERROR_0009, WEEKEND));
//        }
//    }
//
//    private void assignCalendarHolidays(AppSession session, List<String> holidays) throws Exception {
//        String FN_NAME = "assignCalendarHolidays";
//        logger.startDebugLog(session, getClass(), FN_NAME);
//        try {
//            Set<LocalDate> holidaySet = new HashSet<>();
//
//            if (holidays == null) {
//                StringBuilder sb = new StringBuilder();
//                try {
//                    BufferedReader br = new BufferedReader(new FileReader(new File(TIMER_HOLIDAYS_CONFIG_FILE_PATH)));
//                    while (br.ready())
//                        sb.append(" " + br.readLine() + " ");
//                } catch (FileNotFoundException ex) {
//                    logger.error(session, getClass(), FN_NAME, ex.getMessage());
//                    throw new BusinessException(ex, ErrorCodes.GS_IO_0001, TIMER_HOLIDAYS_CONFIG_FILE_PATH);
//                } catch (IOException ex) {
//                    logger.error(session, getClass(), FN_NAME, ex.getMessage());
//                    throw new BusinessException(ex, ErrorCodes.GS_IO_0002, TIMER_HOLIDAYS_CONFIG_FILE_PATH);
//                }
//
//                Pattern r = Pattern.compile("\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])");
//
//                // Now create matcher object.
//                Matcher m = r.matcher(sb.toString());
//                List<String> holidaysProp = new ArrayList<>();
//                int counter = 0;
//                while (m.find())
//                    holidaysProp.add(sb.substring(m.start(), m.end()));
//
//                for (String holiday : holidaysProp) {
//                    holidaySet.add(LocalDate.parse(holiday));
//                    this.holidays.add(holiday);
//                }
//            } else {
//                for (String holiday : holidays)
//                    holidaySet.add(LocalDate.parse(holiday));
//            }
//
//            this.holidayCalendar = new DefaultHolidayCalendar<>();
//            this.holidayCalendar.setHolidays(holidaySet);
//
//            dateCalculator = new LocalDateCalculator("Timer Calender",
//                    null, this.holidayCalendar, new LocalDateForwardHandler());
//            logger.infoLog(session, getClass(), FN_NAME, "Holidays are assigned Successfully in the Timer Calculator");
//
//            logger.endDebugLog(session, getClass(), FN_NAME);
//        } catch (DateTimeParseException ex) {
//            logger.error(session, getClass(), FN_NAME, ex.getMessage());
//            throw new BusinessException(ex, NPCErrorHandler.getMsg(logger, session, NPCError.GS_ERROR_0009, HOLIDAYS));
//        } catch (NullPointerException ex) {
//            logger.error(session, getClass(), FN_NAME, ex.getMessage());
//            throw new BusinessException(ex, NPCErrorHandler.getMsg(logger, session, NPCError.GS_ERROR_0010, HOLIDAYS));
//        }
//    }
//
//    /**
//     * Calculates the dueDate by adding the hours and minutes to the startDate
//     *
//     * @param startDate Start Date to be moved to a dueDate
//     * @param hours     Number of hours to be add to the startDate
//     * @param minutes   Number of minutes to be add to the startDate
//     * @return Due Date after adding the hours and minutes
//     */
//    private LocalDateTime addHoursMinutes(AppSession session, LocalDateTime startDate, int hours, int minutes) throws Exception {
//        final String FN_NAME = "addHoursMinutes";
//        logger.startDebugLog(session, getClass(), FN_NAME, startDate, hours, minutes);
//
//        if (minutes > 60) {
//            logger.infoLog(session, getClass(), FN_NAME, "Minutes to be added to the Start Date {0} passed 1 Hour", minutes);
//            int numberOfHours = minutes / 60;
//            hours += numberOfHours;
//            minutes -= numberOfHours * 60;
//        }
//
//        if (hours > this.workingHours) {
//            logger.infoLog(session, getClass(), FN_NAME, "Hours to be added to the Start Date {0} passed 1 BD", hours);
//            int numberOfDays = hours / this.workingHours;
//            startDate = this.addDays(session, startDate, numberOfDays, false);
//            hours -= numberOfDays * workingHours;
//        }
//
//        logger.infoLog(session, getClass(), FN_NAME, "Start Adding {0} Hours and {1} Minutes to the Start Day", hours, minutes);
//        LocalDateTime dueDateTime = startDate.plusHours(hours).plusMinutes(minutes);
//        logger.infoLog(session, getClass(), FN_NAME, "Due Date after adding Hours & Minutes = {0}", dueDateTime.toString());
//
//        // If the DueDate passed the Working Hours
//        if (((dueDateTime.getHour() == this.endTime.getHour()) && (dueDateTime.getMinute() > 1 || dueDateTime.getSecond() > 1))
//                || ((dueDateTime.getHour() / this.endTime.getHour()) >= 1)) {
//            logger.infoLog(session, getClass(), FN_NAME, "Due Date passed the End of Working Hours");
//            dueDateTime = this.addDays(session, dueDateTime, 1, false).minusHours(workingHours);
//        } else {
//            logger.infoLog(session, getClass(), FN_NAME, "Due Date didn''t pass the End of Working Hours");
//        }
//
//        logger.endDebugLog(session, getClass(), FN_NAME, dueDateTime);
//        return dueDateTime;
//    }
//
//    /**
//     * Calculates the dueDate by adding the days to the startDate taking
//     * with respect to the holidays in the Calendar
//     *
//     * @param startDate Start Date to be moved to a dueDate
//     * @param days      Number of days to be add to the startDate
//     * @return Due Date after adding the days
//     */
//    private LocalDateTime addDays(AppSession session, LocalDateTime startDate, int days, boolean endOfBD) throws Exception {
//        final String FN_NAME = "addDays";
//        logger.startDebugLog(session, getClass(), FN_NAME, startDate, days);
//
//        dateCalculator.setStartDate(startDate.toLocalDate());
//
//        LocalDate dueDate = dateCalculator.moveByBusinessDays(days).getCurrentBusinessDate();
//        LocalDateTime dueDateTime = LocalDateTime.of(dueDate, startDate.toLocalTime());
//        if (endOfBD)
//            dueDateTime = dueDateTime.withHour(endTime.getHour() - 1).withMinute(59).withSecond(59);
//
//        logger.endDebugLog(session, getClass(), FN_NAME, dueDateTime);
//        return dueDateTime;
//    }
//
//    /**
//     * Calculates the dueDate by adding the Timer value to the startDate taking
//     * with respect to the holidays in the Calendar
//     *
//     * @param startDate Start Date to be moved to a dueDate
//     * @param timerUnit timer Unit
//     * @param value     timer value
//     * @return Due Date of the Timer
//     * @throws NPCGeneralException - if Timer Unit isn't supported in the System
//     */
//    public Date calculateDueDate(AppSession session, Date startDate, String timerUnit, int value) throws Exception {
//        final String FN_NAME = "calculateDueDate";
//        logger.startDebugLog(session, getClass(), FN_NAME, startDate, timerUnit, value);
//
//        LocalDateTime startDateTime = LocalDateTime.ofInstant(startDate.toInstant(), ZoneId.systemDefault());
//        LocalDateTime dueDateTime = null;
//
//        TimerUnits timerUnitEnum = getTimerUnit(timerUnit);
//        switch (timerUnitEnum) {
//            case Business_Day:
//                dueDateTime = this.addDays(session, startDateTime, value, true);
//                if (checkInWorkingHour(session, startDateTime).equals(AFTER_WH))
//                    dueDateTime = this.addDays(session, dueDateTime, 1, false);
//                break;
//            case Business_Hour:
//                String checkStartDate = checkInWorkingHour(session, startDateTime);
//                if (checkStartDate.equals(AFTER_WH)) {
//                    startDateTime = this.addDays(session, startDateTime, 1, false);
//                    startDateTime = LocalDateTime.of(startDateTime.toLocalDate(), startTime);
//                } else if (checkStartDate.equals(BEFORE_WH))
//                    startDateTime = LocalDateTime.of(startDateTime.toLocalDate(), startTime);
//                else if (checkStartDate.equals(IN_HOLIDAY)) {
//                    startDateTime = this.addDays(session, startDateTime, 0, false);
//                    startDateTime = LocalDateTime.of(startDateTime.toLocalDate(), startTime);
//                }
//                dueDateTime = this.addHoursMinutes(session, startDateTime, value, 0);
//                break;
//            case Calendar_Day:
//                dueDateTime = startDateTime.plusDays(value);
//                dueDateTime = LocalDateTime.of(dueDateTime.toLocalDate(), LocalTime.of(23, 59, 59));
//                break;
//            case Hour:
//                dueDateTime = startDateTime.plusHours(value);
//                break;
//            case Minute:
//                dueDateTime = startDateTime.plusMinutes(value);
//                break;
//            default:
//                throw new BusinessException(NPCErrorHandler.getMsg(logger, session, NPCError.GS_ERROR_0007));
//        }
//
//        Date dueDate = Date.from(dueDateTime.atZone(ZoneId.systemDefault()).toInstant());
//        logger.endDebugLog(session, getClass(), FN_NAME, dueDate);
//
//        return dueDate;
//    }
//
//    /**
//     * Checks the startDate if it's valid date to start the timer
//     * It could be before or after the working hours or received in a holiday
//     *
//     * @param startDateTime Start Date to be Checked
//     * @return The next valid Start Date
//     */
//    public String checkInWorkingHour(AppSession session, LocalDateTime startDateTime) throws Exception {
//        final String FN_NAME = "checkInWorkingHour";
//        logger.startDebugLog(session, getClass(), FN_NAME, startDateTime);
//
//        LocalTime startTime = startDateTime.toLocalTime();
//        LocalDate startLocalDate = startDateTime.toLocalDate();
//
//        String result;
//
//        if (dateCalculator.isNonWorkingDay(startLocalDate)) {
//            logger.infoLog(session, getClass(), FN_NAME, "Start Date " + startDateTime + " is Weekend");
//            result = IN_HOLIDAY;
//        } else if (this.holidayCalendar.isHoliday(startLocalDate)) {
//            logger.infoLog(session, getClass(), FN_NAME, "Start Date " + startDateTime + " is Holiday");
//            result = IN_HOLIDAY;
//        } else if (startTime.isAfter(this.endTime)) {
//            logger.infoLog(session, getClass(), FN_NAME, "Start Date " + startDateTime + " after working Hours");
//            result = AFTER_WH;
//        } else if (startTime.isBefore(this.startTime)) {
//            logger.infoLog(session, getClass(), FN_NAME, "Start Date " + startDateTime + " before working Hours");
//            result = BEFORE_WH;
//        } else {
//            logger.infoLog(session, getClass(), FN_NAME, "Start Date " + startDateTime + " within working Hours");
//            result = WITHIN_WH;
//        }
//
//        logger.endDebugLog(session, getClass(), FN_NAME, result);
//        return result;
//    }
//
//    public Properties getCalendarConfig() {
//        return calendarConfig;
//    }
//
//    public void setCalendarConfig(Properties calendarConfig) {
//        this.calendarConfig = calendarConfig;
//    }
//
//    public List<String> getHolidays() {
//        return holidays;
//    }
//
//    public void setHolidays(List<String> holidays) {
//        this.holidays = holidays;
//    }
//
//    public Date calculateDueDate(AppSession session, Date startDate, String timerUnit, float value) throws Exception {
//        String FN_NAME = "calculateDueDate";
//        logger.startDebugLog(session, getClass(), FN_NAME, startDate, timerUnit, value);
//
//        if (Float.toString(value).indexOf('.') == -1)
//            return calculateDueDate(session, startDate, timerUnit, (int) value);
//        else if (value <= 1 && timerUnit.equals(TimerUnits.Minute.displayName())) {
//            if (value * 60 >= 30)
//                return calculateDueDate(session, startDate, timerUnit, 1);
//            else
//                return startDate;
//        } else {
//            float decimalValue = (float) Math.ceil(value) - value;
//            int integerValue = (int) (value - decimalValue);
//
//            Date _startDate = calculateDueDate(session, startDate, timerUnit, integerValue);
//
//            TimerUnits timerUnitEnum = getTimerUnit(timerUnit);
//            switch (timerUnitEnum) {
//                case Business_Day:
//                    return calculateDueDate(session, _startDate, Business_Hour.displayName(), decimalValue * 24);
//                case Calendar_Day:
//                    return calculateDueDate(session, _startDate, Hour.displayName(), decimalValue * 24);
//                case Hour:
//                    return calculateDueDate(session, _startDate, Minute.displayName(), decimalValue);
//                case Business_Hour:
//                    return calculateDueDate(session, _startDate, Minute.displayName(), decimalValue);
//                case Minute:
//                    return calculateDueDate(session, _startDate, Minute.displayName(), decimalValue);
//                default:
//                    throw new BusinessException(NPCErrorHandler.getMsg(logger, session, NPCError.GS_ERROR_0007));
//            }
//        }
//    }
//
//}
