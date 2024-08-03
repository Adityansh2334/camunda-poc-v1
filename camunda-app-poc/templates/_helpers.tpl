# templates/_helpers.tpl

{{/*
Create a default set of labels.
*/}}
{{- define "camunda-app-poc.labels" -}}
app.kubernetes.io/name: {{ include "camunda-app-poc.name" . }}
helm.sh/chart: {{ include "camunda-app-poc.chart" . }}
app.kubernetes.io/instance: {{ .Release.Name }}
app.kubernetes.io/managed-by: {{ .Release.Service }}
{{- end }}

{{/*
Return the fully qualified app name.
*/}}
{{- define "camunda-app-poc.fullname" -}}
{{- if .Values.fullnameOverride }}
{{- .Values.fullnameOverride | trunc 63 | trimSuffix "-" }}
{{- else }}
{{- $name := default .Chart.Name .Values.nameOverride }}
{{- printf "%s-%s" $name .Release.Name | trunc 63 | trimSuffix "-" }}
{{- end }}
{{- end }}

{{/*
Return the name of the chart.
*/}}
{{- define "camunda-app-poc.name" -}}
{{- .Chart.Name | trunc 63 | trimSuffix "-" }}
{{- end }}

{{/*
Return the chart name and version.
*/}}
{{- define "camunda-app-poc.chart" -}}
{{- printf "%s-%s" .Chart.Name .Chart.Version | trunc 63 | trimSuffix "-" }}
{{- end }}

{{/*
Generate the name of the service account to use
*/}}
{{- define "camunda-app-poc.serviceAccountName" -}}
{{- if .Values.serviceAccount.create }}
{{- default (include "camunda-app-poc.fullname" .) .Values.serviceAccount.name }}
{{- else }}
{{- .Values.serviceAccount.name }}
{{- end }}
{{- end }}

{{/*
Create a default set of selector labels.
*/}}
{{- define "camunda-app-poc.selectorLabels" -}}
app.kubernetes.io/name: {{ include "camunda-app-poc.name" . }}
app.kubernetes.io/instance: {{ .Release.Name }}
{{- end }}
