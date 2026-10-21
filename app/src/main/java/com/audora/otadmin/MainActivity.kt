package com.audora.otadmin

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.widget.*

class MainActivity : Activity() {
    private val navy = Color.rgb(20, 35, 65)
    private val blue = Color.rgb(42, 103, 220)
    private val bg = Color.rgb(246, 248, 252)
    private lateinit var root: LinearLayout
    private var loggedIn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showLogin()
    }

    private fun text(s: String, size: Float, color: Int = navy, bold: Boolean = false): TextView =
        TextView(this).apply {
            text = s; textSize = size; setTextColor(color)
            if (bold) setTypeface(null, Typeface.BOLD)
            setPadding(0, 6, 0, 6)
        }

    private fun button(s: String, action: () -> Unit): Button =
        Button(this).apply { text = s; setOnClickListener { action() } }

    private fun page(title: String): LinearLayout {
        root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL; setPadding(22, 22, 22, 18); setBackgroundColor(bg)
        }
        val scroll = ScrollView(this)
        scroll.addView(root)
        setContentView(scroll)
        root.addView(text("OT TRACKER PRO", 13f, blue, true))
        root.addView(text(title, 26f, navy, true))
        return root
    }

    private fun showLogin() {
        val p = page("Admin Sign In")
        p.addView(text("Private administration workspace", 15f, Color.DKGRAY))
        val email = EditText(this).apply { hint = "Admin email"; inputType = 33 }
        val pin = EditText(this).apply { hint = "Password"; inputType = 129 }
        p.addView(email); p.addView(pin)
        p.addView(text("Security note: this starter screen is not connected to a real authentication server. Do not use it to protect production data yet.", 13f, Color.rgb(160,70,20)))
        p.addView(button("Open demo dashboard") {
            // Demonstration navigation only; replace with server-verified role-based authentication.
            loggedIn = true
            showDashboard()
        })
        p.addView(text("Before production: connect Firebase Auth or your secured API, then enforce Owner/Admin roles on the server.", 12f, Color.GRAY))
    }

    private fun metric(label: String, value: String) {
        val card = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(16, 10, 16, 10)
            setBackgroundColor(Color.WHITE)
        }
        card.addView(text(label, 14f, Color.GRAY))
        card.addView(text(value, 25f, navy, true))
        val lp = LinearLayout.LayoutParams(-1, -2); lp.setMargins(0, 7, 0, 7)
        root.addView(card, lp)
    }

    private fun section(title: String, description: String, action: () -> Unit) {
        root.addView(text(title, 18f, navy, true))
        root.addView(text(description, 14f, Color.DKGRAY))
        root.addView(button("Open $title", action))
    }

    private fun showDashboard() {
        val p = page("Admin Dashboard")
        p.addView(text("DEMO DATA — connect the backend before real use", 13f, Color.rgb(180,75,20), true))
        metric("Total users", "—")
        metric("Active subscriptions", "—")
        metric("Payments pending review", "—")
        metric("Overtime records", "—")
        section("User Management", "Search users, view account status, suspend/reactivate accounts.") { showModule("User Management", "Requires protected users API and role checks.") }
        section("Overtime Records", "Filter records by user and date; export reports.") { showModule("Overtime Records", "Requires OT records API and report/export service.") }
        section("Subscriptions", "Manage plans, start/end dates, renewals and cancellations.") { showModule("Subscriptions", "Requires subscription service and server-side entitlement checks.") }
        section("Payments", "Review transaction status and record approved refunds/manual payments.") { showModule("Payments", "Never store card details. Verify payment events on the server.") }
        section("Admin Access", "Invite approved admins and assign least-privilege roles.") { showModule("Admin Access", "Owner-only API required. Never grant roles from the Android client alone.") }
        section("Reports & Audit Logs", "View admin activity and export reports.") { showModule("Reports & Audit Logs", "Requires server-side audit logging.") }
        p.addView(button("Sign out") { loggedIn = false; showLogin() })
    }

    private fun showModule(title: String, description: String) {
        val p = page(title)
        p.addView(text(description, 16f))
        p.addView(text("This Android project currently provides the navigation/UI starter. Live data and real admin actions need a secured backend connected to the existing OT Tracker Pro app.", 14f, Color.rgb(160,70,20)))
        p.addView(button("Back to dashboard") { showDashboard() })
    }
}
