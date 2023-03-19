package com.endmysuffering.webapi.ContentTypes;

public class ContentTypes {

    public interface ContentType {
        public String getContentType();
    }

    public enum Application implements ContentType {
        EDI_X12 {
            @Override
            public String getContentType() {
                return "application/EDI-X12";
            }
        },
        EDIFACT {
            @Override
            public String getContentType() {
                return "application/EDIFACT";
            }
        },
        javascript {
            @Override
            public String getContentType() {
                return "application/javascript";
            }
        },
        octet_stream {
            @Override
            public String getContentType() {
                return "application/octet-stream";
            }
        },
        ogg {
            @Override
            public String getContentType() {
                return "application/ogg";
            }
        },
        pdf {
            @Override
            public String getContentType() {
                return "application/pdf";
            }
        },
        xhtml_xml {
            @Override
            public String getContentType() {
                return "application/xhtml+xml";
            }
        },
        x_shockwave_flash {
            @Override
            public String getContentType() {
                return "application/x-shockwave-flash";
            }
        },
        json {
            @Override
            public String getContentType() {
                return "application/json";
            }
        },
        ld_json {
            @Override
            public String getContentType() {
                return "application/ld+json";
            }
        },
        xml {
            @Override
            public String getContentType() {
                return "application/xml";
            }
        },
        zip {
            @Override
            public String getContentType() {
                return "application/zip";
            }
        },
        x_www_form_urlencoded {
            @Override
            public String getContentType() {
                return "application/x-www-form-urlencoded";
            }
        };
    
        public abstract String getContentType();
    }

    public enum Audio implements ContentType{

        mpeg {
            @Override
            public String getContentType() {
                return "audio/mpeg";
            }
        },
        x_ms_wma {
            @Override
            public String getContentType() {
                return "audio/x-ms-wma";
            }
        },
        vnd_rn_realaudio {
            @Override
            public String getContentType() {
                return "audio/vnd.rn-realaudio";
            }
        },
        x_wav {
            @Override
            public String getContentType() {
                return "audio/x-wav";
            }
        };

        public abstract String getContentType();
    }

    public enum Image implements ContentType{
        gif {
            @Override
            public String getContentType() {
                return "image/gif";
            }
        },
        jpeg {
            @Override
            public String getContentType() {
                return "image/jpeg";
            }
        },
        png {
            @Override
            public String getContentType() {
                return "image/png";
            }
        },
        tiff {
            @Override
            public String getContentType() {
                return "image/tiff";
            }
        },
        vnd_microsoft_icon {
            @Override
            public String getContentType() {
                return "image/vnd.microsoft.icon";
            }
        },
        x_icon {
            @Override
            public String getContentType() {
                return "image/x-icon";
            }
        },
        vnd_djvu {
            @Override
            public String getContentType() {
                return "image/vnd.djvu";
            }
        },
        svg_xml {
            @Override
            public String getContentType() {
                return "image/svg+xml";
            }
        };
        public abstract String getContentType();
    }

    public enum Multipart implements ContentType {
        mixed {
            @Override
            public String getContentType() {
                return "multipart/mixed";
            }
        },
        alternative {
            @Override
            public String getContentType() {
                return "multipart/alternative";
            }
        },
        related {
            @Override
            public String getContentType() {
                return "multipart/related";
            }
        },
        form_data {
            @Override
            public String getContentType() {
                return "multipart/form-data";
            }
        };

        
        public abstract String getContentType();
    }

    public enum Text implements ContentType {
        css {
            @Override
            public String getContentType() {
                return "	text/css";
            }
        },
        csv {
            @Override
            public String getContentType() {
                return "text/csv";
            }
        },
        html {
            @Override
            public String getContentType() {
                return "text/html";
            }
        },
        javascript {
            @Override
            public String getContentType() {
                return "text/javascript";
            }
        },
        plain {
            @Override
            public String getContentType() {
                return "text/plain";
            }
        },
        xml {
            @Override
            public String getContentType() {
                return "text/xml";
            }
        };
        
        public abstract String getContentType();
    }

    public enum Video implements ContentType {
        mpeg {
            @Override
            public String getContentType() {
                return "	video/mpeg";
            }
        },
        mp4 {
            @Override
            public String getContentType() {
                return "video/mp4";
            }
        },
        quicktime {
            @Override
            public String getContentType() {
                return "video/quicktime";
            }
        },
        x_ms_wmv {
            @Override
            public String getContentType() {
                return "video/x-ms-wmv";
            }
        },
        x_msvideo {
            @Override
            public String getContentType() {
                return "video/x-msvideo";
            }
        },
        x_flv {
            @Override
            public String getContentType() {
                return "video/x-flv";
            }
        },
        webm {
            @Override
            public String getContentType() {
                return "video/webm";
            }
        };

        public abstract String getContentType();
    }

    public enum VND implements ContentType{
        vnd_oasis_opendocument_text {
            @Override
            public String getContentType() {
                return "application/vnd.oasis.opendocument.text";
            }
        },
        vnd_oasis_opendocument_spreadsheet {
            @Override
            public String getContentType() {
                return "application/vnd.oasis.opendocument.spreadsheet";
            }
        },
        vnd_oasis_opendocument_presentation {
            @Override
            public String getContentType() {
                return "application/vnd.oasis.opendocument.presentation";
            }
        },
        vnd_oasis_opendocument_graphics {
            @Override
            public String getContentType() {
                return "application/vnd.oasis.opendocument.graphics";
            }
        },
        vnd_ms_excel {
            @Override
            public String getContentType() {
                return "application/vnd.ms-excel";
            }
        },
        vnd_openxmlformats_officedocument_spreadsheetml_sheet {
            @Override
            public String getContentType() {
                return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
            }
        },
        vnd_ms_powerpoint {
            @Override
            public String getContentType() {
                return "application/vnd.ms-powerpoint";
            }
        },
        vnd_openxmlformats_officedocument_presentationml_presentation {
            @Override
            public String getContentType() {
                return "application/vnd.openxmlformats-officedocument.presentationml.presentation";
            }
        },
        msword {
            @Override
            public String getContentType() {
                return "application/msword";
            }
        },
        vnd_openxmlformats_officedocument_wordprocessingml_document {
            @Override
            public String getContentType() {
                return "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
            }
        },
        vnd_mozilla_xul_xml {
            @Override
            public String getContentType() {
                return "application/vnd.mozilla.xul+xml";
            }
        };
        
        public abstract String getContentType();
    }
}
