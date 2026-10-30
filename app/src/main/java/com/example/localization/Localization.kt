package com.example.localization

enum class AppLanguage(val code: String, val displayName: String, val englishName: String, val isRtl: Boolean = false) {
    ENGLISH("en", "English", "English", false),
    ARABIC("ar", "العربية", "Arabic", true),
    SPANISH("es", "Español", "Spanish", false),
    GERMAN("de", "Deutsch", "German", false);

    companion object {
        fun fromCode(code: String): AppLanguage {
            return entries.find { it.code.equals(code, ignoreCase = true) } ?: ENGLISH
        }
    }
}

data class AppStrings(
    // App Bar & Navigation
    val appName: String,
    val navFreeTips: String,
    val navVipTips: String,
    val navSettings: String,

    // VIP Grid Titles
    val vip2PlusTitle: String,
    val vip2PlusDesc: String,
    val vip3PlusTitle: String,
    val vip3PlusDesc: String,
    val vip5PlusTitle: String,
    val vip5PlusDesc: String,
    val vipSpecialTitle: String,
    val vipSpecialDesc: String,
    val vipHistoryTitle: String,
    val vipHistoryDesc: String,

    // History Tabs
    val histTab2Plus: String,
    val histTab3Plus: String,
    val histTab5Plus: String,
    val histTabSpecial: String,

    // Card Details
    val oddsLabel: String,
    val kickoffLabel: String,
    val vs: String,
    val accumulatedOddsLabel: String,
    val numberOfOddsLabel: String,

    // Settings
    val settingsTitle: String,
    val settingLanguage: String,
    val settingVersion: String,
    val settingPrivacyPolicy: String,
    val settingAbout: String,
    val settingAdmin: String,
    val selectLanguage: String,

    // Empty States
    val emptyTips: String,
    val emptyHistory: String,
    val emptyAdmin: String,

    // Admin Panel
    val adminTitle: String,
    val adminFilterAll: String,
    val adminFilterFree: String,
    val adminFilterVip: String,
    val markPending: String,
    val markWon: String,
    val markLost: String,
    val resetData: String,
    val resetDataConfirm: String,
    val adminNotice: String,

    // Back & General
    val back: String,
    val cancel: String,
    val confirm: String,

    // Privacy Policy Sections
    val privacyPolicyTitle: String,
    val ppSection1Title: String,
    val ppSection1Body: String,
    val ppSection2Title: String,
    val ppSection2Body: String,
    val ppSection3Title: String,
    val ppSection3Body: String,
    val ppSection4Title: String,
    val ppSection4Body: String,
    val ppSection5Title: String,
    val ppSection5Body: String,
    val ppSection6Title: String,
    val ppSection6Body: String,
    val ppSection7Title: String,
    val ppSection7Body: String,
    val ppSection8Title: String,
    val ppSection8Body: String,

    // About
    val aboutTitle: String,
    val aboutDesc1: String,
    val aboutDesc2: String,
    val aboutDisclaimerTitle: String,
    val aboutDisclaimerBody: String
)

object LocalizationRepository {
    private val englishStrings = AppStrings(
        appName = "Oddsly Predictions",
        navFreeTips = "Free Tips",
        navVipTips = "VIP Tips",
        navSettings = "Settings",

        vip2PlusTitle = "2+ Tips VIP",
        vip2PlusDesc = "Daily selections with odds 2.00+",
        vip3PlusTitle = "3+ Tips VIP",
        vip3PlusDesc = "Medium-tier value tips with odds 3.00+",
        vip5PlusTitle = "5+ Tips VIP",
        vip5PlusDesc = "High odds predictions 5.00+",
        vipSpecialTitle = "Special VIP",
        vipSpecialDesc = "Combos, draws & featured specials",
        vipHistoryTitle = "History VIP",
        vipHistoryDesc = "Full historical archive of completed VIP tips",

        histTab2Plus = "2+ Tips History",
        histTab3Plus = "3+ Tips History",
        histTab5Plus = "5+ Tips History",
        histTabSpecial = "Special VIP History",

        oddsLabel = "Odds",
        kickoffLabel = "Kickoff",
        vs = "vs",
        accumulatedOddsLabel = "Accumulated Odds",
        numberOfOddsLabel = "Number of Odds",

        settingsTitle = "Settings",
        settingLanguage = "Language",
        settingVersion = "Version",
        settingPrivacyPolicy = "Privacy Policy",
        settingAbout = "About",
        settingAdmin = "Result Management (Admin)",
        selectLanguage = "Select Language",

        emptyTips = "No active predictions available at the moment.",
        emptyHistory = "No completed prediction records in this category.",
        emptyAdmin = "No predictions found.",

        adminTitle = "Result Management",
        adminFilterAll = "All",
        adminFilterFree = "Free",
        adminFilterVip = "VIP",
        markPending = "Pending",
        markWon = "Won",
        markLost = "Lost",
        resetData = "Reset Seed Data",
        resetDataConfirm = "Reset all predictions to initial sample state?",
        adminNotice = "Admin Notice: Changing an active VIP tip to Won or Lost will immediately move it to its corresponding VIP History category.",

        back = "Back",
        cancel = "Cancel",
        confirm = "Confirm",

        privacyPolicyTitle = "Privacy Policy",
        ppSection1Title = "1. Introduction",
        ppSection1Body = "Oddsly Predictions provides football predictions, tips, and related informational content. This privacy policy explains how information is handled when users access and use the application.",
        ppSection2Title = "2. Information Collection",
        ppSection2Body = "The application operates entirely on your device and does not collect, transmit, or store any personal information, identifiable records, or device usage profiles on external servers.",
        ppSection3Title = "3. Device Information and Storage",
        ppSection3Body = "Application preferences such as your chosen interface language and locally managed prediction records are stored strictly on your local device to enable full offline and seamless operation.",
        ppSection4Title = "4. Third-Party Services",
        ppSection4Body = "Oddsly Predictions does not integrate third-party advertising SDKs, tracking frameworks, commercial monetization plugins, or external telemetry libraries.",
        ppSection5Title = "5. Data Security",
        ppSection5Body = "We apply reasonable industry measures to maintain application integrity. However, because local storage resides on your device, users are advised to maintain standard device security controls.",
        ppSection6Title = "6. Children's Privacy",
        ppSection6Body = "Oddsly Predictions is designed for general informational sports audiences. It does not solicit or knowingly collect personal information from children under the age of 13.",
        ppSection7Title = "7. Policy Changes",
        ppSection7Body = "This privacy policy may be updated occasionally. Any revisions will be reflected directly within the installed application updates.",
        ppSection8Title = "8. Contact Information",
        ppSection8Body = "For inquiries regarding this privacy policy or application operation, please consult the official support channels provided by the application publisher.",

        aboutTitle = "About Oddsly Predictions",
        aboutDesc1 = "Oddsly Predictions is a focused, clean, and reliable football predictions and tips application created for sports enthusiasts.",
        aboutDesc2 = "Users can browse daily Free Tips, access five dedicated VIP prediction categories (2+ Tips VIP, 3+ Tips VIP, 5+ Tips VIP, Special VIP, and History VIP), inspect completed match records, and follow football events worldwide with zero payment walls or subscriptions.",
        aboutDisclaimerTitle = "Important Disclaimer",
        aboutDisclaimerBody = "All predictions, tips, statistics, and analyses presented in Oddsly Predictions are purely informational opinions. Football match outcomes are inherently uncertain, bookmaker odds may fluctuate at any time, and no prediction guarantees any result, outcome, or profit. This application does not offer real-money gambling or wagering services."
    )

    private val arabicStrings = AppStrings(
        appName = "Oddsly Predictions",
        navFreeTips = "توقعات مجانية",
        navVipTips = "توقعات VIP",
        navSettings = "الإعدادات",

        vip2PlusTitle = "VIP +2 توقعات",
        vip2PlusDesc = "توقعات يومية بمعامل 2.00+",
        vip3PlusTitle = "VIP +3 توقعات",
        vip3PlusDesc = "توقعات متوازنة بمعامل 3.00+",
        vip5PlusTitle = "VIP +5 توقعات",
        vip5PlusDesc = "توقعات بمعاملات مرتفعة 5.00+",
        vipSpecialTitle = "VIP مميز",
        vipSpecialDesc = "توقعات مركبة ومباريات خاصة",
        vipHistoryTitle = "أرشيف VIP",
        vipHistoryDesc = "السجل التاريخي لجميع التوقعات المكتملة",

        histTab2Plus = "أرشيف VIP +2",
        histTab3Plus = "أرشيف VIP +3",
        histTab5Plus = "أرشيف VIP +5",
        histTabSpecial = "أرشيف VIP المميز",

        oddsLabel = "المعامل",
        kickoffLabel = "وقت المباراة",
        vs = "ضد",
        accumulatedOddsLabel = "إجمالي المعامل المتراكم",
        numberOfOddsLabel = "عدد المعاملات",

        settingsTitle = "الإعدادات",
        settingLanguage = "اللغة",
        settingVersion = "الإصدار",
        settingPrivacyPolicy = "سياسة الخصوصية",
        settingAbout = "حول التطبيق",
        settingAdmin = "إدارة النتائج (المسؤول)",
        selectLanguage = "اختر اللغة",

        emptyTips = "لا توجد توقعات متاحة حالياً.",
        emptyHistory = "لا توجد نتائج مكتملة في هذا القسم.",
        emptyAdmin = "لم يتم العثور على أي توقعات.",

        adminTitle = "إدارة النتائج",
        adminFilterAll = "الكل",
        adminFilterFree = "مجاني",
        adminFilterVip = "VIP",
        markPending = "قيد الانتظار",
        markWon = "فوز",
        markLost = "خسارة",
        resetData = "استعادة البيانات الافتراضية",
        resetDataConfirm = "هل تريد إعادة تعيين جميع التوقعات إلى الحالة النموذجية؟",
        adminNotice = "ملاحظة المسؤول: يؤدي تحديد توقع VIP كفوز أو خسارة إلى نقله تلقائياً إلى قسم الأرشيف التاريخي المقابل.",

        back = "رجوع",
        cancel = "إلغاء",
        confirm = "تأكيد",

        privacyPolicyTitle = "سياسة الخصوصية",
        ppSection1Title = "1. المقدمة",
        ppSection1Body = "يوفر تطبيق Oddsly Predictions توقعات ونصائح كروية ومحتوى معلوماتي ذي صلة. توضح سياسة الخصوصية هذه كيفية التعامل مع المعلومات أثناء استخدام التطبيق.",
        ppSection2Title = "2. جمع المعلومات",
        ppSection2Body = "يعمل التطبيق بشكل كامل محلياً على جهازك ولا يجمع أو ينقل أو يخزن أي بيانات شخصية أو سجلات تعريفية على خوادم خارجية.",
        ppSection3Title = "3. معلومات الجهاز والتخزين",
        ppSection3Body = "يتم حفظ تفضيلات التطبيق مثل لغة الواجهة وسجلات التوقعات المحلية فقط على جهازك لضمان عمل التطبيق بسلاسة دون اتصال بالإنترنت.",
        ppSection4Title = "4. خدمات الطرف الثالث",
        ppSection4Body = "لا يتضمن تطبيق Oddsly Predictions حزم إعلانات أو أدوات تتبع أو ملحقات تجارية من أطراف ثالثة.",
        ppSection5Title = "5. أمان البيانات",
        ppSection5Body = "نتبع إجراءات معيارية للمحافظة على كفاءة التطبيق. ونظراً لأن البيانات محفوظة محلياً، ننصح المستخدمين بالحفاظ على تدابير أمان أجهزتهم الشخصية.",
        ppSection6Title = "6. خصوصية الأطفال",
        ppSection6Body = "التطبيق موجه للجمهور الرياضي العام ولا يجمع معلومات شخصية عن الأطفال دون سن 13 عاماً.",
        ppSection7Title = "7. تعديلات السياسة",
        ppSection7Body = "قد يتم تحديث سياسة الخصوصية عند الضرورة، وستنعكس أي تعديلات مباشرة ضمن تحديثات التطبيق الرسمية.",
        ppSection8Title = "8. معلومات الاتصال",
        ppSection8Body = "لأي استفسارات حول سياسة الخصوصية أو تشغيل التطبيق، يرجى مراجعة قنوات الدعم الرسمية التابعة لناشر التطبيق.",

        aboutTitle = "حول Oddsly Predictions",
        aboutDesc1 = "تطبيق Oddsly Predictions هو تطبيق متخصص وموثوق لتوقعات كرة القدم مصمم لمحبي الرياضة.",
        aboutDesc2 = "يمكن للمستخدمين تصفح التوقعات المجانية اليومية، والوصول إلى أقسام VIP الخمسة المتاحة مجاناً، واستعراض نتائج المباريات المكتملة دون قيود أو اشتراكات.",
        aboutDisclaimerTitle = "إخلاء مسؤولية هام",
        aboutDisclaimerBody = "جميع التوقعات والإحصائيات والتحليلات الواردة في التطبيق هي آراء ومعلومات فقط. نتائج مباريات كرة القدم غير مضمونة، والاحتمالات قد تتغير في أي وقت، ولا يضمن أي توقع تحقيق ربح أو نتيجة مؤكدة. هذا التطبيق لا يقدم خدمات مراهنات بأموال حقيقية."
    )

    private val spanishStrings = AppStrings(
        appName = "Oddsly Predictions",
        navFreeTips = "Pronósticos Gratis",
        navVipTips = "Pronósticos VIP",
        navSettings = "Ajustes",

        vip2PlusTitle = "VIP +2 Cuotas",
        vip2PlusDesc = "Selecciones diarias con cuota 2.00+",
        vip3PlusTitle = "VIP +3 Cuotas",
        vip3PlusDesc = "Consejos equilibrados con cuota 3.00+",
        vip5PlusTitle = "VIP +5 Cuotas",
        vip5PlusDesc = "Pronósticos de alta cuota 5.00+",
        vipSpecialTitle = "VIP Especial",
        vipSpecialDesc = "Combinadas, empates y eventos especiales",
        vipHistoryTitle = "Historial VIP",
        vipHistoryDesc = "Archivo histórico completo de consejos VIP",

        histTab2Plus = "Historial VIP +2",
        histTab3Plus = "Historial VIP +3",
        histTab5Plus = "Historial VIP +5",
        histTabSpecial = "Historial VIP Especial",

        oddsLabel = "Cuota",
        kickoffLabel = "Hora",
        vs = "vs",
        accumulatedOddsLabel = "Cuota Acumulada",
        numberOfOddsLabel = "Número de Cuotas",

        settingsTitle = "Ajustes",
        settingLanguage = "Idioma",
        settingVersion = "Versión",
        settingPrivacyPolicy = "Política de Privacidad",
        settingAbout = "Acerca de",
        settingAdmin = "Gestión de Resultados (Admin)",
        selectLanguage = "Seleccionar Idioma",

        emptyTips = "No hay pronósticos activos disponibles en este momento.",
        emptyHistory = "No hay registros históricos en esta categoría.",
        emptyAdmin = "No se encontraron pronósticos.",

        adminTitle = "Gestión de Resultados",
        adminFilterAll = "Todos",
        adminFilterFree = "Gratis",
        adminFilterVip = "VIP",
        markPending = "Pendiente",
        markWon = "Ganado",
        markLost = "Perdido",
        resetData = "Restablecer Datos Iniciales",
        resetDataConfirm = "¿Desea restablecer todos los pronósticos a los datos de muestra iniciales?",
        adminNotice = "Aviso de administrador: Al declarar un pronóstico VIP como Ganado o Perdido se moverá automáticamente a su categoría de Historial VIP correspondiente.",

        back = "Volver",
        cancel = "Cancelar",
        confirm = "Confirmar",

        privacyPolicyTitle = "Política de Privacidad",
        ppSection1Title = "1. Introducción",
        ppSection1Body = "Oddsly Predictions ofrece pronósticos, consejos de fútbol y contenido informativo deportivo. Esta política explica el tratamiento de la información al utilizar la aplicación.",
        ppSection2Title = "2. Recopilación de Información",
        ppSection2Body = "La aplicación funciona de forma completamente local en su dispositivo y no recopila, transmite ni almacena datos personales en servidores externos.",
        ppSection3Title = "3. Información del Dispositivo y Almacenamiento",
        ppSection3Body = "Las preferencias de la aplicación, como el idioma seleccionado y los registros de pronósticos locales, se almacenan estrictamente en su dispositivo para permitir un funcionamiento óptimo sin conexión.",
        ppSection4Title = "4. Servicios de Terceros",
        ppSection4Body = "Oddsly Predictions no integra kits de publicidad de terceros, rastreadores ni librerías de telemetría comercial externa.",
        ppSection5Title = "5. Seguridad de los Datos",
        ppSection5Body = "Aplicamos medidas técnicas estándar para preservar la integridad de la aplicación. Al residir los datos en el dispositivo, se recomienda mantener las medidas habituales de seguridad del sistema.",
        ppSection6Title = "6. Privacidad de Menores",
        ppSection6Body = "La aplicación está diseñada para el público deportivo general y no solicita ni recopila conscientemente información de menores de 13 años.",
        ppSection7Title = "7. Modificaciones de la Política",
        ppSection7Body = "Esta política puede actualizarse ocasionalmente. Las revisiones se reflejarán directamente en las actualizaciones de la aplicación.",
        ppSection8Title = "8. Información de Contacto",
        ppSection8Body = "Para consultas sobre esta política de privacidad o el funcionamiento de la aplicación, consulte los canales oficiales de soporte del editor.",

        aboutTitle = "Acerca de Oddsly Predictions",
        aboutDesc1 = "Oddsly Predictions es una aplicación enfocada, limpia y confiable de pronósticos de fútbol diseñada para aficionados al deporte.",
        aboutDesc2 = "Los usuarios pueden consultar pronósticos gratuitos diarios, acceder a las cinco categorías VIP (VIP +2, VIP +3, VIP +5, VIP Especial e Historial VIP), revisar resultados históricos y seguir partidos sin suscripciones ni pagos.",
        aboutDisclaimerTitle = "Aviso Importante",
        aboutDisclaimerBody = "Todos los pronósticos, consejos y análisis presentados en Oddsly Predictions son opiniones meramente informativas. Los resultados de los partidos de fútbol son inciertos, las cuotas pueden variar en cualquier momento y ningún pronóstico garantiza resultados o ganancias. Esta aplicación no ofrece apuestas con dinero real."
    )

    private val germanStrings = AppStrings(
        appName = "Oddsly Predictions",
        navFreeTips = "Kostenlose Tipps",
        navVipTips = "VIP Tipps",
        navSettings = "Einstellungen",

        vip2PlusTitle = "2+ Tipps VIP",
        vip2PlusDesc = "Tägliche Auswahlen mit Quoten ab 2.00+",
        vip3PlusTitle = "3+ Tipps VIP",
        vip3PlusDesc = "Ausgewogene Tipps mit Quoten ab 3.00+",
        vip5PlusTitle = "5+ Tipps VIP",
        vip5PlusDesc = "Hohe Quoten Vorhersagen ab 5.00+",
        vipSpecialTitle = "Spezial VIP",
        vipSpecialDesc = "Kombis, Unentschieden und Spezialtipps",
        vipHistoryTitle = "VIP Verlauf",
        vipHistoryDesc = "Vollständiges Archiv aller beendeten VIP-Tipps",

        histTab2Plus = "2+ Tipps Verlauf",
        histTab3Plus = "3+ Tipps Verlauf",
        histTab5Plus = "5+ Tipps Verlauf",
        histTabSpecial = "Spezial VIP Verlauf",

        oddsLabel = "Quote",
        kickoffLabel = "Anstoß",
        vs = "gg.",
        accumulatedOddsLabel = "Gesamtquote",
        numberOfOddsLabel = "Anzahl der Quoten",

        settingsTitle = "Einstellungen",
        settingLanguage = "Sprache",
        settingVersion = "Version",
        settingPrivacyPolicy = "Datenschutzerklärung",
        settingAbout = "Über die App",
        settingAdmin = "Ergebnisverwaltung (Admin)",
        selectLanguage = "Sprache auswählen",

        emptyTips = "Aktuell sind keine Vorhersagen verfügbar.",
        emptyHistory = "Keine beendeten Vorhersagen in dieser Kategorie vorhanden.",
        emptyAdmin = "Keine Vorhersagen gefunden.",

        adminTitle = "Ergebnisverwaltung",
        adminFilterAll = "Alle",
        adminFilterFree = "Gratis",
        adminFilterVip = "VIP",
        markPending = "Ausstehend",
        markWon = "Gewonnen",
        markLost = "Verloren",
        resetData = "Standarddaten wiederherstellen",
        resetDataConfirm = "Möchten Sie alle Vorhersagen auf die Standardbeispieldaten zurücksetzen?",
        adminNotice = "Admin-Hinweis: Wenn ein aktiver VIP-Tipp als Gewonnen oder Verloren markiert wird, wandert er automatisch in das zugehörige VIP-Verlaufsarchiv.",

        back = "Zurück",
        cancel = "Abbrechen",
        confirm = "Bestätigen",

        privacyPolicyTitle = "Datenschutzerklärung",
        ppSection1Title = "1. Einleitung",
        ppSection1Body = "Oddsly Predictions stellt Fußballvorhersagen, Tipps und bezogene Informationsinhalte bereit. Diese Erklärung beschreibt den Umgang mit Informationen bei der Nutzung der App.",
        ppSection2Title = "2. Informationserhebung",
        ppSection2Body = "Die Anwendung arbeitet vollständig lokal auf Ihrem Gerät und erhebt, übermittelt oder speichert keine personenbezogenen Daten auf externen Servern.",
        ppSection3Title = "3. Geräteinformationen und Speicherung",
        ppSection3Body = "Einstellungen wie die gewählte Sprache und lokale Tippdaten werden ausschließlich lokal auf Ihrem Endgerät gespeichert, um eine zuverlässige Offline-Funktion zu gewährleisten.",
        ppSection4Title = "4. Drittanbieter-Dienste",
        ppSection4Body = "Oddsly Predictions bindet keine Werbenetzwerke von Drittanbietern, Tracking-Dienste oder kommerzielle Telemetrie-Bibliotheken ein.",
        ppSection5Title = "5. Datensicherheit",
        ppSection5Body = "Wir ergreifen angemessene Maßnahmen zur Gewährleistung der Anwendungssicherheit. Da die Daten lokal auf Ihrem Gerät liegen, empfehlen wir gängige Sicherheitsstandards auf dem Gerät.",
        ppSection6Title = "6. Schutz der Privatsphäre von Kindern",
        ppSection6Body = "Die App richtet sich an ein allgemeines sportinteressiertes Publikum und erhebt wissentlich keine Daten von Kindern unter 13 Jahren.",
        ppSection7Title = "7. Änderungen der Richtlinie",
        ppSection7Body = "Diese Datenschutzerklärung kann gelegentlich aktualisiert werden. Änderungen werden direkt in App-Updates eingepflegt.",
        ppSection8Title = "8. Kontaktinformationen",
        ppSection8Body = "Bei Fragen zu dieser Datenschutzerklärung oder der Anwendung wenden Sie sich bitte an die offiziellen Supportkanäle des Herausgebers.",

        aboutTitle = "Über Oddsly Predictions",
        aboutDesc1 = "Oddsly Predictions ist eine kompakte, verlässliche Fußball-Tipp-App für Sportbegeisterte.",
        aboutDesc2 = "Nutzer können tägliche kostenlose Tipps abrufen, alle fünf VIP-Kategorien (2+ Tipps VIP, 3+ Tipps VIP, 5+ Tipps VIP, Spezial VIP und VIP Verlauf) kostenfrei nutzen und Spielstatistiken ohne Abos oder Zahlungen verfolgen.",
        aboutDisclaimerTitle = "Wichtiger Hinweis & Haftungsausschluss",
        aboutDisclaimerBody = "Alle Vorhersagen, Quoten und Analysen in Oddsly Predictions sind rein unverbindliche Meinungen. Fußballergebnisse sind naturgemäß unvorhersehbar, Quoten können sich ändern, und kein Tipp garantiert einen Gewinn oder Erfolg. Diese App bietet kein Echtgeld-Glücksspiel an."
    )

    fun getStrings(language: AppLanguage): AppStrings {
        return when (language) {
            AppLanguage.ENGLISH -> englishStrings
            AppLanguage.ARABIC -> arabicStrings
            AppLanguage.SPANISH -> spanishStrings
            AppLanguage.GERMAN -> germanStrings
        }
    }
}
